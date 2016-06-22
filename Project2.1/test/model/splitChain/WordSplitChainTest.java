package model.splitChain;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class WordSplitChainTest {

    static SplitChain wordSplitChain;

    @BeforeClass
    public static void init() {
        wordSplitChain = (SplitChain) GlobalContext.getParam(GlobalContext.WORD_SPLIT_STRATEGY_KEY);
    }

    @Test
    public void testSplitForNextChain() throws Exception {
        checkSplit("assignment");
        checkSplit("well-paid");
        checkSplit("");
        checkSplit(null);
    }

    @Test
    public void testGroup() throws Exception {
        checkGroup("assignment");
        checkGroup("well-paid");
    }

    private void checkSplit(String str) {
        if (str == null || str.trim().isEmpty()) {
            Assert.assertArrayEquals(new String[0], wordSplitChain.splitForNextChain(str).toArray());
        } else {
            Assert.assertArrayEquals(str.split(""), wordSplitChain.splitForNextChain(str).toArray());
        }
    }

    private void checkGroup(String str) {
        if (str == null) {
            Assert.assertEquals("", wordSplitChain.group(wordSplitChain.build(str)));
        } else {
            Assert.assertEquals(str, wordSplitChain.group(wordSplitChain.build(str)));
        }
    }
}