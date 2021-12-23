package com.langtest.langtest.content;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WordTest {

    @Test
    void shouldClassifyAsVerb() {
        Word verb = new Word();
        verb.setContentStr("verbd");
        verb.buildSubContentNodes();
        assertEquals(WordType.VERB, verb.getType());
    }

    @Test
    void shouldClassifyAsNoun() {
        Word noun = new Word();
        noun.setContentStr("sdfaasd");
        noun.buildSubContentNodes();
        assertEquals(WordType.NOUN, noun.getType());
    }

    @Test
    void shouldClassifyAsPrep() {
        Word prep = new Word();
        prep.setContentStr("ver");
        prep.buildSubContentNodes();
        assertEquals(WordType.PREPOSITION, prep.getType());
    }

    @Test
    void shouldClassifyAsInvalid() {
        Word invalid = new Word();
        invalid.setContentStr("verbdsdfaf");
        invalid.buildSubContentNodes();
        assertNull(invalid.getType());
    }

}
