package com.langtest.langtest.content;

import java.util.List;
import java.util.Map;

public interface ContentTree {

    <T extends Content> List<T> getSubContentNodes();

    void buildSubContentNodes();

    Map<String, Integer> getSubValidNodesCount();
}
