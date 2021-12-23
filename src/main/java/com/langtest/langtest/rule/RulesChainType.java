package com.langtest.langtest.rule;

import lombok.Getter;

@Getter
public enum RulesChainType {
    WORD_RULES_CHAIN("wordRulesChain"),
    SENTENCE_RULES_CHAIN("sentenceRulesChain"),
    PARAGRAPH_RULES_CHAIN("paragraphRulesChain");

    private final String beanName;

    RulesChainType(String beanName){
        this.beanName = beanName;
    }
}
