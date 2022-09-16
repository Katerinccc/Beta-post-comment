package com.posada.santiago.betapostsandcomments.imp.business.gateways.model;

import lombok.Data;

@Data
public class CommentViewModel {
    private String commentId;
    private String postId;
    private String author;
    private String content;

    public CommentViewModel() {
    }

    public CommentViewModel(String commentId, String postId, String author, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentViewModel{" +
                "commentId='" + commentId + '\'' +
                ", postId='" + postId + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
