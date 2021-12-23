package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Rule1 extends Rule {

    @Value("#{'${word.alphabet}'.split(',')}")
    private Set<Character> alphabet;

    @Override
    public boolean obeyRule(Content content) {
        return isCharactersInAlphabet(content);
    }

    private boolean isCharactersInAlphabet(Content word) {
        String string = word.getContentStr();
        for(int i = 0;i<string.length();i++){
            if(!(alphabet.contains(string.charAt(i))||
                    alphabet.contains(Character.toUpperCase(string.charAt(i))))){
                return false;
            }
        }
        return true;
    }


    @Override
    public String getRuleName() {
        return "Rule 1";
    }

}
