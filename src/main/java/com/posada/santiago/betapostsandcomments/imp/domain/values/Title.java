package com.posada.santiago.betapostsandcomments.imp.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Title implements ValueObject<String> {

    private final String title;

    public Title(String title) {
        this.title = title;
    }

    @Override
    public String value() {
        return title;
    }
}
