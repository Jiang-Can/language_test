package com.langtest.langtest.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParagraphText {

    private Paragraph paragraph1;

    private Paragraph paragraph2;

    @BeforeEach
    void init() {
        String normalStr = "  dsfgsoliciu Mauhis arcusu semihe. ir digil quisam impediec es macir quisua. Nullam quir poral ac merul!";
        String invalidStr = "  dsfgsoliciu... !  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua . . .. Nullam quir poral ac merul   .";
        paragraph1 = new Paragraph();
        paragraph2 = new Paragraph();
        paragraph1.setContentStr(invalidStr);
        paragraph2.setContentStr(normalStr);
        paragraph1.buildSubContentNodes();
        paragraph2.buildSubContentNodes();
    }

    @Test
    void shouldBuildCorrectNumOfSentences() {
        assertEquals(5, paragraph1.getSubContentNodes().size());
        assertEquals(3, paragraph2.getSubContentNodes().size());
    }

    @Test
    void shouldGetCorrectEndExclamationMark() {
        assertEquals('.', paragraph1.getEndSign());
        assertEquals('!', paragraph2.getEndSign());
    }

    @Test
    void shouldReturnCorrectValidCount() {
        assertEquals(4, paragraph1.getSubValidNodesCount().get("Sentences"));
        assertEquals(3, paragraph2.getSubValidNodesCount().get("Sentences"));
    }
}
