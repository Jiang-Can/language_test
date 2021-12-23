package com.langtest.langtest.rule.rules;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.content.Word;
import com.langtest.langtest.content.WordType;
import com.langtest.langtest.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Rule3 extends Rule {

    @Value("#{'${word.verb.endWith}'.split(',')}")
    private Set<Character> verbEndChars;

    @Override
    public boolean obeyRule(Content content) {
        return isVerbEndWithChars((Word) content);
    }

    private boolean isVerbEndWithChars(Word word) {

        if (word.getType() == null || !word.getType().equals(WordType.VERB)) {
            return true;
        }
        char lastChar = word.getContentStr().charAt(word.getContentStr().length() - 1);
        return verbEndChars.contains(lastChar) ||
                verbEndChars.contains(Character.toUpperCase(lastChar));
    }

    @Override
    public String getRuleName() {
        return "Rule 3";
    }
}
