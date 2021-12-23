package com.langtest.langtest.content;


import com.langtest.langtest.util.TextSplitter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Paragraph extends Content {

    private final List<Sentence> sentences = new ArrayList<>();

    @Getter
    private char endSign;

    @Override
    public List<Sentence> getSubContentNodes() {
        return sentences;
    }

    @Override
    public void buildSubContentNodes() {
        buildSubContentNodesHelper(TextSplitter.splitParagraphToSentences(getContentStr()), Sentence.class);
        endSign = getContentStr().charAt(getContentStr().length() - 1);
        setValid(getValidCount() > 0);
    }

    @Override
    public Map<String, Integer> getSubValidNodesCount() {
        Map<String,Integer> count = new HashMap<>();
        count.put("Sentences",getValidCount());
        return count;
    }


}
