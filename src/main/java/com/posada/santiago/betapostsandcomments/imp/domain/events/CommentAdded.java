package com.posada.santiago.betapostsandcomments.imp.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class CommentAdded extends DomainEvent {

    private String id;
    private String author;
    private String content;


    public CommentAdded(String commentId, String author, String content) {
        super("posada.santiago.commentcreated");
        this.id = commentId;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
