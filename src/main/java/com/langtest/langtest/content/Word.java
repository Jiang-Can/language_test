package com.langtest.langtest.content;

import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Word extends Content {

    @Getter
    private WordType type = null;

    @Override
    public List<Word> getSubContentNodes() {
        return null;
    }

    @Override
    public void buildSubContentNodes() {
        int strLen = getContentStr().length();
        if (strLen >= 1 && strLen <= 3) {
            type = WordType.PREPOSITION;
        } else if (strLen <= 6) {
            type = WordType.VERB;
        } else if (strLen <= 8) {
            type = WordType.NOUN;
        }
        setValid(type != null);
    }

    @Override
    public Map<String, Integer> getSubValidNodesCount() {
        return null;
    }


}


