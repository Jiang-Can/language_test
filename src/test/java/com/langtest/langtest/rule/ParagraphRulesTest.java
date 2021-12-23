package com.langtest.langtest.rule;

import com.langtest.langtest.content.Paragraph;
import com.langtest.langtest.rule.rules.Rule5;
import com.langtest.langtest.rule.rules.Rule6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("text")
@SpringBootTest(classes = {Rule5.class, Rule6.class})
public class ParagraphRulesTest {

    @Autowired
    private Rule5 rule5;

    @Autowired
    private Rule6 rule6;

    private Paragraph validParagraph;
    private Paragraph invalidParagraph;

    @BeforeEach
    void init(){
        String validStr = "  dsfgsoliciu Mauhis arcusu semihe. ir digil quisam impediec es macir quisua. Nullam quir poral ac merul!";
        String invalidStr = "  dsfgsoliciu... !  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua . . .. Nullam quir poral ac merul   .";
        validParagraph = new Paragraph();
        invalidParagraph = new Paragraph();
        validParagraph.setContentStr(validStr);
        invalidParagraph.setContentStr(invalidStr);
        validParagraph.buildSubContentNodes();
        invalidParagraph.buildSubContentNodes();
    }

    @Test
    void checkRule5(){
        assertTrue(rule5.obeyRule(validParagraph));
        assertFalse(rule5.obeyRule(invalidParagraph));
    }

    @Test
    void checkRule6(){
        assertTrue(rule6.obeyRule(validParagraph));
        assertFalse(rule6.obeyRule(invalidParagraph));
    }
}
