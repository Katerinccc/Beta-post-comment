package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;


import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.bus.RabbitMqEventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.generic.DomainUpdater;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.PostCreated;
import org.springframework.stereotype.Service;

@Service
public class ViewUpdater extends DomainUpdater {

    private final DomainViewRepository repository;
    private final RabbitMqEventBus eventBus;

    public ViewUpdater(DomainViewRepository repository, RabbitMqEventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;

        listen((PostCreated event) -> {
            PostViewModel postView = new PostViewModel(
                    event.aggregateRootId(),
                    event.getAuthor(),
                    event.getTitle()
            );
            repository.saveNewPost(postView).subscribe();
            eventBus.publishPostCreated(postView);
        });

        listen((CommentAdded event) -> {
            CommentViewModel commentView = new CommentViewModel(
                    event.getId(),
                    event.aggregateRootId(),
                    event.getAuthor(),
                    event.getContent()
            );
            repository.addCommentToPost(commentView).subscribe();
            eventBus.publishCommentAdded(commentView);
        });

    }
}
