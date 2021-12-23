package com.langtest.langtest.util;


import com.langtest.langtest.dto.ResultDto;
import com.langtest.langtest.dto.ResultUnit;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class ResultMapper {

    public ResultDto mapResultToResultDto(Map<String, Integer> types, Map<String, Integer> violations) {
        return ResultDto.builder()
                .status(violations.size() > 0 ? "INVALID" : "VALID")
                .types(mapToList(types,
                        new String[]{"Paragraphs", "Sentences", "Verbs", "Nouns", "Prepositions"}))
                .violations(mapToList(violations,
                        new String[]{"Rule 1", "Rule 2", "Rule 3", "Rule 4", "Rule 5", "Rule 6"}))
                .build();
    }

    private List<ResultUnit> mapToList(Map<String, Integer> types, String[] constantsArray) {
        List<ResultUnit> list = new LinkedList<>();
        for (String str : constantsArray) {
            list.add(new ResultUnit(str, types.get(str) == null ? 0 : types.get(str)));
        }
        return list;
    }
}
