package model.entity;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class WordTest {

    static SplitChain wordSplitter = (SplitChain) GlobalContext.getParam(GlobalContext.WORD_SPLIT_STRATEGY_KEY);

    @Test
    public void testFindWords() throws Exception {
        Word word = (Word) wordSplitter.build("word");
        Assert.assertArrayEquals(new TextPart[]{word}, word.findWords(4).toArray());
        Assert.assertArrayEquals(new TextPart[0], word.findWords(6).toArray());
        Assert.assertArrayEquals(new TextPart[0], word.findWords(-2).toArray());
        Assert.assertArrayEquals(new TextPart[0], word.findWords(0).toArray());
    }
}