package com.langtest.langtest.rule;

import com.langtest.langtest.content.Sentence;
import com.langtest.langtest.rule.rules.Rule4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {Rule4.class})
public class SentenceRuleTest {

    @Autowired
    private Rule4 rule4;

    @Test
    void checkRule4(){
        String validSentenceStr = "asd sdmn in sdasfghj";
        String invalidSentenceStr = "asd sdmnsd in";
        Sentence validSentence = new Sentence();
        Sentence invalidSentence = new Sentence();
        validSentence.setContentStr(validSentenceStr);
        invalidSentence.setContentStr(invalidSentenceStr);
        validSentence.buildSubContentNodes();
        invalidSentence.buildSubContentNodes();

        assertTrue(rule4.obeyRule(validSentence));
        assertFalse(rule4.obeyRule(invalidSentence));
    }
}
