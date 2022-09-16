package com.posada.santiago.betapostsandcomments.imp.business.gateways.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
    @Override
    public String toString() {
        return "PostViewModel{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", comments=" + comments +
                '}';
    }
}
