package com.langtest.langtest.rule;

import com.langtest.langtest.content.Content;

import java.util.HashMap;
import java.util.Map;

public class RulesChain {

    private Rule headRule = null;

    private Rule tailRule = null;

    public void addRule(Rule rule) {
        rule.setSuccessorRule(null);
        if (headRule == null) {
            headRule = rule;
            tailRule = rule;
            return;
        }
        tailRule.setSuccessorRule(rule);
        tailRule = rule;
    }

    public Map<String, Integer> checkRules(Content content) {
        if (headRule == null) {
            return null;
        }
        Map<String, Integer> report = new HashMap<>();
        Rule curRule = headRule;
        while (curRule != null) {
            if (!curRule.obeyRule(content)) {
                accumulateResult(report, curRule.getRuleName());
            }
            curRule = curRule.nextRule();
        }
        return report;
    }

    private void accumulateResult(Map<String, Integer> map, String key) {
        map.put(key, map.get(key) == null ? 1 : map.get(key) + 1);
    }
}
