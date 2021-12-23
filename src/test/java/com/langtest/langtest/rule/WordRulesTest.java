package com.langtest.langtest.rule;


import com.langtest.langtest.content.Word;
import com.langtest.langtest.rule.rules.Rule1;
import com.langtest.langtest.rule.rules.Rule2;
import com.langtest.langtest.rule.rules.Rule3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("text")
@SpringBootTest(classes = {Rule1.class, Rule2.class, Rule3.class})
public class WordRulesTest {

    @Autowired
    private Rule1 rule1;

    @Autowired
    private Rule2 rule2;

    @Autowired
    private Rule3 rule3;

    @Test
    void  checkRule1(){
        Word validWord = new Word();
        validWord.setContentStr("abcdefg");
        validWord.buildSubContentNodes();
        Word invalidWord = new Word();
        invalidWord.setContentStr("abcdefx");
        invalidWord.buildSubContentNodes();
        assertTrue(rule1.obeyRule(validWord));
        assertFalse(rule1.obeyRule(invalidWord));
    }

    @Test
    void checkRule2(){
        Word validWord = new Word();
        validWord.setContentStr("abcdefg");
        validWord.buildSubContentNodes();
        Word invalidWord = new Word();
        invalidWord.setContentStr("abcdefgasdfa");
        invalidWord.buildSubContentNodes();
        assertTrue(rule2.obeyRule(validWord));
        assertFalse(rule2.obeyRule(invalidWord));
    }

    @Test
    void checkRule3(){
        Word validVerb = new Word();
        validVerb.setContentStr("abcd");
        validVerb.buildSubContentNodes();
        Word invalidVerb = new Word();
        invalidVerb.setContentStr("abce");
        invalidVerb.buildSubContentNodes();
        Word notVerb = new Word();
        notVerb.setContentStr("in");
        invalidVerb.buildSubContentNodes();
        assertTrue(rule3.obeyRule(validVerb));
        assertFalse(rule3.obeyRule(invalidVerb));
        assertTrue(rule3.obeyRule(notVerb));
    }
}
