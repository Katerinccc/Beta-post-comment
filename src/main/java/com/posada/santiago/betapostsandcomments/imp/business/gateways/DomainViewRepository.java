package com.posada.santiago.betapostsandcomments.imp.business.gateways;

import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainViewRepository {
    Mono<PostViewModel> findByPostId(String postId);
    Flux<PostViewModel> findAllPosts();
    Mono<PostViewModel> saveNewPost(PostViewModel post);
    Mono<PostViewModel> addCommentToPost(CommentViewModel comment);
}
