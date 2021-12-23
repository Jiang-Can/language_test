package com.langtest.langtest.rule;

import com.langtest.langtest.content.Paragraph;
import com.langtest.langtest.content.Sentence;
import com.langtest.langtest.content.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
public class RulesChainsTest {

    @Autowired
    private ApplicationContext context;

    private RulesChain wordRulesChain;

    private RulesChain sentenceRulesChain;

    private RulesChain paragraphRulesChain;

    @BeforeEach
    void init(){
        wordRulesChain = (RulesChain) context.getBean(RulesChainType.WORD_RULES_CHAIN.getBeanName());
        sentenceRulesChain = (RulesChain) context.getBean(RulesChainType.SENTENCE_RULES_CHAIN.getBeanName());
        paragraphRulesChain = (RulesChain) context.getBean(RulesChainType.PARAGRAPH_RULES_CHAIN.getBeanName());
    }

    @Test
    void testWordRulesChain(){
        Word violateTwoRules = new Word();
        violateTwoRules.setContentStr("abxx");
        violateTwoRules.buildSubContentNodes();
        Word valid = new Word();
        valid.setContentStr("abcdefg");
        valid.buildSubContentNodes();
        Map<String, Integer> invalidResult = wordRulesChain.checkRules(violateTwoRules);
        Map<String, Integer> validResult = wordRulesChain.checkRules(valid);
        assertEquals(1,invalidResult.get("Rule 1"));
        assertEquals(1,invalidResult.get("Rule 3"));
        assertEquals(0,validResult.size());
    }

    @Test
    void testSentenceRulesChain(){
        Sentence violateRule = new Sentence();
        violateRule.setContentStr("asd sdmnsd in");
        violateRule.buildSubContentNodes();
        Sentence valid = new Sentence();
        valid.setContentStr("asd sdmn in sdasfghj");
        valid.buildSubContentNodes();
        Map<String, Integer> invalidResult = sentenceRulesChain.checkRules(violateRule);
        Map<String, Integer> validResult = sentenceRulesChain.checkRules(valid);
        assertEquals(1,invalidResult.get("Rule 4"));
        assertEquals(0,validResult.size());
    }

    @Test
    void testParagraphRulesChain(){
        Paragraph violateTwoRules = new Paragraph();
        violateTwoRules.setContentStr("dsfgsoliciu... !  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua . . .. Nullam quir poral ac merul   .");
        violateTwoRules.buildSubContentNodes();
        Paragraph valid = new Paragraph();
        valid.setContentStr("dsfgsoliciu Mauhis arcusu semihe. ir digil quisam impediec es macir quisua. Nullam quir poral ac merul!");
        valid.buildSubContentNodes();
        Map<String, Integer> invalidResult = paragraphRulesChain.checkRules(violateTwoRules);
        Map<String, Integer> validResult = paragraphRulesChain.checkRules(valid);
        assertEquals(1,invalidResult.get("Rule 5"));
        assertEquals(1,invalidResult.get("Rule 6"));
        assertEquals(0,validResult.size());
    }



}
