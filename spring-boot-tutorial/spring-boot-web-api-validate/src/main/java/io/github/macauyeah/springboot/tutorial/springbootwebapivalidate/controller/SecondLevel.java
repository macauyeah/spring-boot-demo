package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SecondLevel {
    @NotNull
    @NotEmpty
    private String nonNullString;

    public String getNonNullString() {
        return nonNullString;
    }

    public void setNonNullString(String nonNullString) {
        this.nonNullString = nonNullString;
    }

}
