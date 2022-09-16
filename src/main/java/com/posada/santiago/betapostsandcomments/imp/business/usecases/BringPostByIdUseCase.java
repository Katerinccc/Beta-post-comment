package com.posada.santiago.betapostsandcomments.imp.business.usecases;


import com.mongodb.Function;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BringPostByIdUseCase implements Function<String, Mono<PostViewModel>> {

    private final DomainViewRepository repository;

    public BringPostByIdUseCase(DomainViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PostViewModel> apply(String postId) {
        return repository.findByPostId(postId)
                .doOnNext(responseRepository -> log.info(responseRepository.toString()));
    }


}
