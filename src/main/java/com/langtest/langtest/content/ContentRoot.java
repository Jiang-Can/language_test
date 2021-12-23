package com.langtest.langtest.content;

import com.langtest.langtest.util.TextSplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentRoot extends Content {

    private final List<Paragraph> paragraphs = new ArrayList<>();

    public ContentRoot(String contentStr) {
        setContentStr(contentStr);
    }

    @Override
    public List<Paragraph> getSubContentNodes() {
        return paragraphs;
    }

    @Override
    public void buildSubContentNodes() {
        buildSubContentNodesHelper(TextSplitter.splitTextToParagraphs(getContentStr()), Paragraph.class);
        setValid(getValidCount() > 0);
    }

    @Override
    public Map<String, Integer> getSubValidNodesCount() {
        Map<String,Integer> count = new HashMap<>();
        count.put("Paragraphs",getValidCount());
        return count;
    }

}
