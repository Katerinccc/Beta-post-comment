package com.posada.santiago.betapostsandcomments.imp.application.adapters.repository;

import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

        return mongoTemplate.findOne(query, PostViewModel.class);
    }

    @Override
    public Flux<PostViewModel> findAllPosts() {
        return mongoTemplate.findAll(PostViewModel.class);
    }

    @Override
    public Mono<PostViewModel> saveNewPost(PostViewModel post) {
        return mongoTemplate.save(post);
    }

    @Override
    public Mono<PostViewModel> addCommentToPost(CommentViewModel comment) {

        return findByPostId(comment.getPostId())
            .flatMap(post -> {
                post.addCommentToExistentPost(comment);
            return mongoTemplate.save(post);
        });

    }
}
