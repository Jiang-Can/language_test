package com.langtest.langtest.content;

import lombok.Getter;

@Getter
public enum WordType {
    VERB("Verbs"),
    NOUN("Nouns"),
    PREPOSITION("Prepositions");

    private final String name;

    WordType(String name) {
        this.name = name;
    }

}
