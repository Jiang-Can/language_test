package com.langtest.langtest.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSplitter {

    public static List<String> splitTextToParagraphs(String text){
        return splitAndTrim(text.split("\n|\r\n|\r"));
    }

    public static List<String> splitParagraphToSentences(String paragraph){
        return splitAndTrim(paragraph.split("\\.|\\!"));
    }

    public static List<String> splitSentenceToWords(String sentence){
        return splitAndTrim(sentence.split("\\s+"));
    }

    private static List<String> splitAndTrim(String[] rawStrArr){
        return Arrays.stream(rawStrArr).map(String::trim).collect(Collectors.toList());
    }
}
