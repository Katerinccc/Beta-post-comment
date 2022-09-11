package com.posada.santiago.betapostsandcomments.imp.business.usecases;

import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BringPostByIdUseCaseTest {

    @Mock
    DomainViewRepository repository;

    @InjectMocks
    BringPostByIdUseCase useCase;

    @Test
    void apply() {

        String postId = "81aa4056-75ba-40cd-bee";

        var postViewModelReturn = new PostViewModel(
                              postId,
                        "Katerin Calderon",
                          "My fist post to test"
        );

        BDDMockito.when(repository.findByPostId(Mockito.any(String.class)))
                .thenReturn(Mono.just(postViewModelReturn));

        var publisher = useCase.apply(postId);

        StepVerifier.create(publisher)
                .expectSubscription()
                .expectNext(postViewModelReturn)
                .verifyComplete();

        Mockito.verify(repository).findByPostId(postId);

    }
}