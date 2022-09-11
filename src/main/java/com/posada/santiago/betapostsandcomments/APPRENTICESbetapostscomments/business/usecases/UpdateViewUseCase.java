package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class UpdateViewUseCase implements Consumer<DomainEvent> {

    private final ViewUpdater viewUpdater;

    public UpdateViewUseCase( ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    @Override
    public void accept(DomainEvent event) {
        viewUpdater.applyEvent(event);
    }
}
