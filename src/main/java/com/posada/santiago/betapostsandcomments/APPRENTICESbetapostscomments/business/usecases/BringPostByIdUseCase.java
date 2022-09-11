package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;


import com.mongodb.Function;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BringPostByIdUseCase implements Function<String, Mono<PostViewModel>> {

    private final DomainViewRepository repository;

    public BringPostByIdUseCase(DomainViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PostViewModel> apply(String postId) {
        return repository.findByPostId(postId);
    }


}
