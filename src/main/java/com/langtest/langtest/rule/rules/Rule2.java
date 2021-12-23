package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Rule2 extends Rule {

    @Value("${word.maxLen}")
    private int wordMaxLen;

    @Override
    public boolean obeyRule(Content content) {
        return isWordLengthOverLimitation(content);
    }

    private boolean isWordLengthOverLimitation(Content word){
        return word.getContentStr().length()<=wordMaxLen;
    }

    @Override
    public String getRuleName() {
        return "Rule 2";
    }
}
