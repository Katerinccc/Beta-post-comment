package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel {

    private String id;
    private String postId;
    private String author;
    private String title;
    private List<CommentViewModel> comments;

    public PostViewModel(String postId, String author, String title) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.comments = new ArrayList<>();
    }

    public void addCommentToExistentPost(CommentViewModel newComment){
        this.comments.add(newComment);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }
}
