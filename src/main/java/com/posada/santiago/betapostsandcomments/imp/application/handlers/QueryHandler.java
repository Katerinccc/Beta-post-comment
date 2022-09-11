package com.posada.santiago.betapostsandcomments.imp.application.handlers;


import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.usecases.BringAllPostsUseCase;
import com.posada.santiago.betapostsandcomments.imp.business.usecases.BringPostByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QueryHandler {

    @Bean
    public RouterFunction<ServerResponse> getPostById(BringPostByIdUseCase useCase){
        return route(
                GET("/beta/post/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                useCase.apply(request.pathVariable("id")), PostViewModel.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse>getAllPost(BringAllPostsUseCase useCase) {
        return route(
                GET("allposts"),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get() , PostViewModel.class))
        );

    }

}


