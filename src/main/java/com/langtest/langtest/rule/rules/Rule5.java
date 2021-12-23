package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Rule5 extends Rule {

    @Value("${paragraph.maxSentences}")
    private int maxSentences;

    @Value("${paragraph.minSentences}")
    private int minSentences;

    @Override
    public boolean obeyRule(Content content) {
        return isSentencesInRange(content);
    }

    private boolean isSentencesInRange(Content paragraph) {
        int sentencesNum = paragraph.getSubContentNodes().size();
        return sentencesNum >= minSentences && sentencesNum <= maxSentences;
    }

    @Override
    public String getRuleName() {
        return "Rule 5";
    }
}
