package com.posada.santiago.betapostsandcomments.imp.business.usecases;

import com.posada.santiago.betapostsandcomments.imp.application.adapters.bus.RabbitMqEventBus;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.generic.DomainUpdater;
import com.posada.santiago.betapostsandcomments.imp.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.imp.domain.events.PostCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
            log.info("Post saved: " + postView);
            eventBus.publishPostCreated(postView);
            log.info("Message send to rabbit: " + postView);
        });

        listen((CommentAdded event) -> {
            CommentViewModel commentView = new CommentViewModel(
                    event.getId(),
                    event.aggregateRootId(),
                    event.getAuthor(),
                    event.getContent()
            );
            repository.addCommentToPost(commentView).subscribe();
            log.info("Comment added: " + commentView);
            eventBus.publishCommentAdded(commentView);
            log.info("Message send to rabbit: " + commentView);
        });

    }
}
