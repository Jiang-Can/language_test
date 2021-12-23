package com.langtest.langtest.rule;

import com.langtest.langtest.content.Content;

public abstract class Rule {

    protected Rule successorRule;

    abstract public boolean obeyRule(Content content);

    abstract public String getRuleName();

    public void setSuccessorRule(Rule rule){
        successorRule = rule;
    }

    public Rule nextRule(){
        return successorRule;
    }
}
