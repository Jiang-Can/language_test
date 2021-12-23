package com.langtest.langtest.config;

import com.langtest.langtest.rule.Rule;
import com.langtest.langtest.rule.RulesChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RulesChainConfig {

    private final ApplicationContext context;

    @Bean(value = "wordRulesChain")
    public RulesChain getWordRulesChain() {
        return buildRulesChain("rule1", "rule2", "rule3");
    }

    @Bean(value = "sentenceRulesChain")
    public RulesChain getSentenceRulesChain() {
        return buildRulesChain("rule4");
    }

    @Bean(value = "paragraphRulesChain")
    public RulesChain getParagraphRulesChain() {
        return buildRulesChain("rule5", "rule6");
    }

    private RulesChain buildRulesChain(String... rules) {
        RulesChain rulesChain = new RulesChain();
        Rule newRule;
        for (String rule : rules) {
            newRule = (Rule) context.getBean(rule);
            rulesChain.addRule(newRule);
        }
        return rulesChain;
    }
}
