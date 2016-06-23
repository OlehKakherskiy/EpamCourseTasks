package model.entity;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentenceTest {

    static SplitChain sentenceSplitter = (SplitChain) GlobalContext.getParam(GlobalContext.SENTENCE_SPLIT_STRATEGY_KEY);

    static SplitChain wordSplitter = (SplitChain) GlobalContext.getParam(GlobalContext.WORD_SPLIT_STRATEGY_KEY);

    @Test
    public void testFindWords() throws Exception {
        String sentence1 = "Ever since the release of Java 5, I've been keeping my eyes open for " +
                "a book that describes what I believe to be the most powerful new feature of Java - generics!??";

        String sentence2 = "Ever since the release of Java 5, I've been keeping my eyes open for " +
                "a book that describes what I believe to be the most powerful new feature of Java - generics.";
        check(prepareExpectations("Ever", "Java", "I've", "been", "eyes", "open", "book", "that", "what", "most"), sentence1, 4);
        check(prepareExpectations("of", "my", "to", "be"), sentence1, 2);
        check(new TextPart[0], sentence1, 20);
        check(new TextPart[0], sentence1, 0);
        check(new TextPart[0], sentence1, -1);

        check(new TextPart[0], sentence2, 4);
    }

    private void check(TextPart[] expectation, String text, int length) {
        Assert.assertArrayEquals(expectation, sentenceSplitter.build(text).findWords(length).toArray());
    }

    private TextPart[] prepareExpectations(String... words) {
        TextPart[] res = new TextPart[words.length];
        Arrays.asList(words).stream().map(this::buildWord).collect(Collectors.toSet()).toArray(res);
        return res;
    }

    private TextPart buildWord(String word) {
        return wordSplitter.build(word);
    }
}