package com.posada.santiago.betapostsandcomments.imp.business.gateways.model;

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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}