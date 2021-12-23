package com.langtest.langtest.content;

import com.langtest.langtest.util.TextSplitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SentenceTest {

    private Sentence sentence;

    private List<String> wordsStr;

    @BeforeEach
    void init() {
        String sentenceString = "Cufabiu maffas nonad   in auguec finibu soliciu";
        sentence = new Sentence();
        sentence.setContentStr(sentenceString);
        wordsStr = TextSplitter.splitSentenceToWords(sentenceString);
        sentence.buildSubContentNodes();
    }

    @Test
    void shouldBuildCorrectWords() {
        List<Word> words = sentence.getSubContentNodes();
        for (int i = 0; i < words.size(); i++) {
            assertEquals(wordsStr.get(i), words.get(i).getContentStr());
        }
        assertEquals(WordType.VERB, words.get(1).getType());
        assertTrue(words.get(2).isValid());
        assertEquals(wordsStr.size(), words.size());
        assertTrue(sentence.isValid());
    }

    @Test
    void shouldReturnCorrectSubNodesCount() {
        Map<String, Integer> count = sentence.getSubValidNodesCount();
        assertEquals(4, count.get("Verbs"));
        assertEquals(1, count.get("Prepositions"));
        assertEquals(2, count.get("Nouns"));
    }
}
