package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.content.Word;
import com.langtest.langtest.content.WordType;
import com.langtest.langtest.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Rule4 extends Rule {


    @Override
    public boolean obeyRule(Content content) {
        return containAllWordTypes(content);
    }

    private boolean containAllWordTypes(Content sentence){
        if(sentence.getSubContentNodes().size()<3) {
            return false;
        }
        Set<WordType> set = new HashSet<>();
        for(Content word: sentence.getSubContentNodes()){
            if(word.isValid()){
                set.add(((Word) word).getType());
            }
        }
        return set.size() == 3;
    }

    @Override
    public String getRuleName() {
        return "Rule 4";
    }
}
