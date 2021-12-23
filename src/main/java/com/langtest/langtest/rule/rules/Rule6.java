package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.content.Paragraph;
import com.langtest.langtest.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Rule6 extends Rule {

    @Value("${paragraph.endSign}")
    private String endSigh;

    @Override
    public boolean obeyRule(Content content) {
        return isEndSignLegal((Paragraph) content);
    }

    private boolean isEndSignLegal(Paragraph paragraph){
        return paragraph.getEndSign() == endSigh.charAt(0);
    }

    @Override
    public String getRuleName() {
        return "Rule 6";
    }
}
