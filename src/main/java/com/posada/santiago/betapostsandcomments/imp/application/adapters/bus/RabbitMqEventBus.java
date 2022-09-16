package com.posada.santiago.betapostsandcomments.imp.application.adapters.bus;


import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.imp.application.config.RabbitMqConfig;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public RabbitMqEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void publishPostCreated(PostViewModel viewModel) {

        var gson = new Gson().toJson(viewModel);

        log.info("Message send to rabbit queue post created: " + gson);

        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE, RabbitMqConfig.PROXY_ROUTING_KEY_POST_CREATED, gson.getBytes()
        );
    }

    @Override
    public void publishCommentAdded(CommentViewModel viewModel) {

        var gson = new Gson().toJson(viewModel);

        log.info("Message send to rabbit queue comment added: " + gson);

        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE, RabbitMqConfig.PROXY_ROUTING_KEY_COMMENT_ADDED, gson.getBytes()
        );
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }
}
