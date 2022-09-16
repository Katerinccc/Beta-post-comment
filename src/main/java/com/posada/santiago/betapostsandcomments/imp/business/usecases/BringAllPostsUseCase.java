package com.posada.santiago.betapostsandcomments.imp.business.usecases;


import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.function.Supplier;

@Slf4j
@Service
public class BringAllPostsUseCase implements Supplier<Flux<PostViewModel>> {

    private final DomainViewRepository repository;

    public BringAllPostsUseCase(DomainViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<PostViewModel> get() {
        return this.repository.findAllPosts()
                .doOnNext(responseRepository -> log.info(responseRepository.toString()));
    }
}
