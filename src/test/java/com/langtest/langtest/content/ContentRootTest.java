package com.langtest.langtest.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentRootTest {

    private ContentRoot contentRoot1;

    private ContentRoot contentRoot2;

    @BeforeEach
    void init() {
        String normalStr = "  dsfgsoliciu Mauhis arcusu semihe. ir digil quisam impediec es macir quisua. \r\n Nullam quir poral ac merul!";
        String invalidStr = "  dsfgsoliciu... ! \n  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua . . .. Nullam quir poral ac merul   .";
        contentRoot1 = new ContentRoot(normalStr);
        contentRoot2 = new ContentRoot(invalidStr);
        contentRoot1.buildSubContentNodes();
        contentRoot2.buildSubContentNodes();
    }

    @Test
    void shouldBuildCorrectNumsOfParagraphs(){
        assertEquals(2,contentRoot1.getSubContentNodes().size());
        assertEquals(2,contentRoot2.getSubContentNodes().size());
    }

    @Test
    void returnCorrectValidCount(){
        assertEquals(2,contentRoot1.getSubValidNodesCount().get("Paragraphs"));
        assertEquals(1,contentRoot2.getSubValidNodesCount().get("Paragraphs"));
    }

}
