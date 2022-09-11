package com.posada.santiago.betapostsandcomments.imp.business.gateways;


import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.imp.business.gateways.model.PostViewModel;

public interface EventBus {
    void publishPostCreated(PostViewModel viewModel);
    void publishCommentAdded(CommentViewModel viewModel);

    void publishError(Throwable errorModel);
}
