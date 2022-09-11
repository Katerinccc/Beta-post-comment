package com.posada.santiago.betapostsandcomments.imp.business.usecases;

import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BringAllPostsUseCaseTest {

    @Mock
    DomainViewRepository repository;

    @InjectMocks
    BringAllPostsUseCase useCase;

    @Test
    void get() {

        var postView1 = new PostViewModel("9f9b1c85-8bab-4889", "Kate", "Test post");
        var postView2 =  new PostViewModel("1ec6a8e0-2794-4304-bc31", "Stephany", "Next post");

        var fluxReturn = Flux.just(postView1, postView2);

        BDDMockito.when(repository.findAllPosts()).thenReturn(fluxReturn);

        var publisher = useCase.get();

        StepVerifier.create(publisher)
                .expectSubscription()
                .expectNext(postView1, postView2)
                .verifyComplete();

        BDDMockito.verify(repository, BDDMockito.times(1)).findAllPosts();

    }
}