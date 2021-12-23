package com.langtest.langtest.service;

import com.langtest.langtest.content.*;
import com.langtest.langtest.dto.ResultDto;
import com.langtest.langtest.rule.RulesChain;
import com.langtest.langtest.rule.RulesChainType;
import com.langtest.langtest.util.ResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class CheckTextService {

    private final ApplicationContext context;

    private final ResultMapper mapper;

    private Map<Class<? extends Content>, RulesChain> rulesChainMap;

    private final ThreadLocal<Map<String, Integer>> typesResult = new ThreadLocal<>();

    private final ThreadLocal<Map<String, Integer>> violationsResult = new ThreadLocal<>();

    @PostConstruct
    private void initializeRulesChainMap() {
        rulesChainMap = new HashMap<>();
        rulesChainMap.put(Paragraph.class,
                (RulesChain) context.getBean(RulesChainType.PARAGRAPH_RULES_CHAIN.getBeanName()));
        rulesChainMap.put(Sentence.class,
                (RulesChain) context.getBean(RulesChainType.SENTENCE_RULES_CHAIN.getBeanName()));
        rulesChainMap.put(Word.class,
                (RulesChain) context.getBean(RulesChainType.WORD_RULES_CHAIN.getBeanName()));
    }

    public ResultDto checkText(String text) {
        ContentRoot contentRoot = new ContentRoot(text);
        contentRoot.buildSubContentNodes();
        generateResults(contentRoot);
        ResultDto resultDto = mapper.mapResultToResultDto(typesResult.get(), violationsResult.get());
        typesResult.remove();
        violationsResult.remove();
        return resultDto;
    }

    private void generateResults(Content contentRoot) {
        typesResult.set(new HashMap<>());
        violationsResult.set(new HashMap<>());
        Content node;
        RulesChain rulesChain;
        Queue<Content> parentNodes = new LinkedList<>();
        parentNodes.add(contentRoot);
        Queue<Content> subNodes = new LinkedList<>();

        while (!parentNodes.isEmpty()) {
            while (!parentNodes.isEmpty()) {
                node = parentNodes.poll();
                if (node.getSubContentNodes() != null) {
                    subNodes.addAll(node.getSubContentNodes());
                    accumulateResult(node.getSubValidNodesCount(), typesResult.get());
                }
                rulesChain = rulesChainMap.get(node.getClass());
                if (rulesChain != null) {
                    accumulateResult(rulesChain.checkRules(node), violationsResult.get());
                }
            }
            parentNodes.addAll(subNodes);
            subNodes.clear();
        }
    }

    private void accumulateResult(Map<String, Integer> map, Map<String, Integer> result) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.put(entry.getKey(), result.get(entry.getKey()) == null ?
                    entry.getValue() : result.get(entry.getKey()) + entry.getValue());
        }
    }


}
