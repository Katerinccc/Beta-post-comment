package com.posada.santiago.betapostsandcomments.imp.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.betapostsandcomments.imp.application.adapters.bus.RabbitMqEventBus;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.imp.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.imp.domain.events.PostCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class UpdateViewUseCaseTest {

    @Mock
    DomainViewRepository repository;
    @Mock
    RabbitMqEventBus eventBus;
    ViewUpdater viewUpdater;
    UpdateViewUseCase useCase;

    @BeforeEach
    void init(){
        viewUpdater = new ViewUpdater(repository, eventBus);
        useCase = new UpdateViewUseCase(viewUpdater);
    }

    @Test
    void acceptCreatePostCase(){

        var postCreatedEvent = new PostCreated("Post for test", "Katerin Calderon");
        postCreatedEvent.setAggregateRootId("bc31-cf74b627bf54");

        PostViewModel postReturn = new PostViewModel(postCreatedEvent.aggregateRootId(),
                                    postCreatedEvent.getAuthor(),
                                    postCreatedEvent.getTitle());

        BDDMockito.when(repository.saveNewPost(Mockito.any(PostViewModel.class)))
                .thenReturn(Mono.just(postReturn));

        useCase.accept(postCreatedEvent);

        BDDMockito.verify(eventBus, BDDMockito.times(1))
                .publishPostCreated(Mockito.any(PostViewModel.class));

        BDDMockito.verify(repository, BDDMockito.times(1))
                .saveNewPost(Mockito.any(PostViewModel.class));

    }

    @Test
    void acceptCommentAdded(){

        var commentAddedEvent = new CommentAdded("958e7644-fa0e-4c8e",
                "Katerin Calderon",
                "Comment for test"
        );
        commentAddedEvent.setAggregateRootId("bc31-cf74b627bf54");

        CommentViewModel commentToAdd = new CommentViewModel(commentAddedEvent.getId(),
                commentAddedEvent.aggregateRootId(),
                commentAddedEvent.getAuthor(),
                commentAddedEvent.getContent()
        );

        PostViewModel postReturn = new PostViewModel(commentAddedEvent.aggregateRootId(),
                                    "Katerin Calderon",
                                    "Post to test with comment"
        );

        postReturn.addCommentToExistentPost(commentToAdd);

        BDDMockito.when(repository.addCommentToPost(Mockito.any(CommentViewModel.class)))
                .thenReturn(Mono.just(postReturn));

        useCase.accept(commentAddedEvent);

        BDDMockito.verify(eventBus, BDDMockito.times(1))
                .publishCommentAdded(Mockito.any(CommentViewModel.class));

        BDDMockito.verify(repository, BDDMockito.times(1))
                .addCommentToPost(Mockito.any(CommentViewModel.class));

    }

}