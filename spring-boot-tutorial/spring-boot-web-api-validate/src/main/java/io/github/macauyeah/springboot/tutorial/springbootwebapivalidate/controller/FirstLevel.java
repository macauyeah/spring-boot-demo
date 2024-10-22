package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FirstLevel {
    @NotNull
    @NotEmpty
    private String nonNullString;
    @Valid
    private SecondLevel secondLevel;

    public String getNonNullString() {
        return nonNullString;
    }

    public void setNonNullString(String nonNullString) {
        this.nonNullString = nonNullString;
    }

    public SecondLevel getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(SecondLevel secondLevel) {
        this.secondLevel = secondLevel;
    }

}
