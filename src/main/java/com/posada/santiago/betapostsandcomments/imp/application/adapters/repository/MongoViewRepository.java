package com.posada.santiago.betapostsandcomments.imp.application.adapters.repository;

import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class MongoViewRepository implements DomainViewRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    private final Gson gson = new Gson();

    public MongoViewRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<PostViewModel> findByPostId(String postId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postId").is(postId));

        return mongoTemplate.findOne(query, PostViewModel.class)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new Throwable("Post id do not exist " + postId))));
    }

    @Override
    public Flux<PostViewModel> findAllPosts() {
        return mongoTemplate.findAll(PostViewModel.class)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new Throwable("Failed to return posts"))));
    }

    @Override
    public Mono<PostViewModel> saveNewPost(PostViewModel post) {
        return mongoTemplate.save(post)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new Throwable("Failed to save post with id" + post.getPostId()))))
                .doOnNext(postSave -> log.info("Post saved: " + postSave.toString()));
    }

    @Override
    public Mono<PostViewModel> addCommentToPost(CommentViewModel comment) {

        return findByPostId(comment.getPostId())
            .flatMap(post -> {
                post.addCommentToExistentPost(comment);
            return mongoTemplate.save(post)
                    .switchIfEmpty(Mono.defer(() ->
                        Mono.error(
                                new Throwable("Failed to add comment to post with id" + comment.getPostId()))
                    ))
                    .doOnNext(commentAdded ->
                            log.info("Comment added to post with id: " + commentAdded.getPostId()));
        });

    }
}
