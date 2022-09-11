package com.posada.santiago.betapostsandcomments.imp.application.handlers;

import co.com.sofka.domain.generic.DomainEvent;
import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.imp.application.adapters.bus.Notification;
import com.posada.santiago.betapostsandcomments.imp.business.usecases.UpdateViewUseCase;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class QueueHandler implements Consumer<String> {

    private final Gson gson = new Gson();
    private final UpdateViewUseCase useCase;

    public QueueHandler(UpdateViewUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void accept(String received) {

        Notification receivedNotification = gson.fromJson(received, Notification.class);

        String type = receivedNotification
                .getType()
                .replace("alphapostsandcomments" ,
                        "betapostsandcomments.imp");

        try {
            DomainEvent domainEvent =
                    (DomainEvent) gson.fromJson(receivedNotification.getBody(), Class.forName(type));
            useCase.accept(domainEvent);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
