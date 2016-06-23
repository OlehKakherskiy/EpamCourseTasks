package model.splitChain;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentenceSplitChainTest {

    static SentenceSplitChain sentenceSplitChain;

    @BeforeClass
    public static void setUp() throws Exception {
        sentenceSplitChain = (SentenceSplitChain) GlobalContext.getParam(GlobalContext.SENTENCE_SPLIT_STRATEGY_KEY);
    }

    @Test
    public void testSplitForNextChain() throws Exception {
        String test = "Ever since the, release of Java 5     , I've been a " +
                "Java - generics!??";

        String[] expectations = {"Ever", "since", "the", ",", "release", "of", "Java", "5", ",",
                "I've", "been", "a", "Java", "-", "generics", "!??"};

        check(test, expectations);

        check(null, new String[0]);
        check("", new String[0]);
    }

    private void check(String test, String[] expectations) {
        Assert.assertArrayEquals(expectations, sentenceSplitChain.splitForNextChain(test).toArray());
    }
}