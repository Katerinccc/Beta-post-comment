package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class UpdateViewUseCase implements Consumer<DomainEvent> {

    private final EventBus eventBus;
    private final ViewUpdater viewUpdater;

    public UpdateViewUseCase(EventBus eventBus, ViewUpdater viewUpdater) {
        this.eventBus = eventBus;
        this.viewUpdater = viewUpdater;
    }

    @Override
    public void accept(DomainEvent event) {
        //eventBus.publish(event);
        viewUpdater.applyEvent(event);
    }
}
