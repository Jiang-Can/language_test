package com.langtest.langtest.content;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.util.List;

@Getter
@Setter
public abstract class Content implements ContentTree {

    private String contentStr;

    private boolean valid;

    private int validCount = 0;

    protected void addCount() {
        validCount++;
    }

    protected <T extends Content> void buildSubContentNodesHelper(List<String> stringList, Class<T> clazz) {
        T temp;
        for (String str : stringList) {
            if (str.length() == 0) {
                continue;
            }
            temp = getNewContent(str, clazz);
            temp.buildSubContentNodes();
            if (temp.isValid()) {
                addCount();
            }
            getSubContentNodes().add(temp);
        }
    }

    private <T extends Content> T getNewContent(String contentStr, Class<T> clazz) {
        T content = null;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            content = constructor.newInstance();
            content.setContentStr(contentStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
