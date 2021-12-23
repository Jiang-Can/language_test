package com.langtest.langtest.content;

import com.langtest.langtest.util.TextSplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sentence extends Content {

    private final List<Word> words = new ArrayList<>();

    @Override
    public List<Word> getSubContentNodes() {
        return words;
    }

    @Override
    public void buildSubContentNodes() {
        buildSubContentNodesHelper(TextSplitter.splitSentenceToWords(getContentStr()), Word.class);
        setValid(getValidCount() > 0);
    }

    @Override
    public Map<String, Integer> getSubValidNodesCount() {
        Map<String, Integer> count = new HashMap<>();
        String key;
        for (Word word : words) {
            if(word.getType()==null){
                continue;
            }
            key = word.getType().getName();
            count.put(key, count.get(key) == null ? 1 : count.get(key) + 1);
        }
        return count;
    }

}
