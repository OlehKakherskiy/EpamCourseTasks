package model.entity;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CompositeTextPartTest {

    static SplitChain sentenceSplitChain;

    static SplitChain wordSplitChain;

    static SplitChain textSplitChain;

    TextPart sentence;

    @BeforeClass
    public static void init() throws Exception {
        sentenceSplitChain = ((SplitChain) GlobalContext.getParam(GlobalContext.SENTENCE_SPLIT_STRATEGY_KEY));
        wordSplitChain = ((SplitChain) GlobalContext.getParam(GlobalContext.WORD_SPLIT_STRATEGY_KEY));
        textSplitChain = (SplitChain) GlobalContext.getParam(GlobalContext.TEXT_SPLIT_STRATEGY_KEY);
    }

    @Before
    public void setUp() throws Exception {
        String test = "Ever since the release of Java 5,      I've been keeping my eyes open for a book that describes what " +
                "I believe to be the most powerful new feature of Java - generics !??";
        sentence = sentenceSplitChain.build(test);
    }

    @Test
    public void testFormat() throws Exception {
        String test = "Ever since the release of Java 5 , I've been keeping my eyes open for a book that describes what " +
                "I believe to be the most powerful new feature of Java - generics !??";
        Assert.assertEquals(test, sentence.format());
//        Assert.assertEquals("", GlobalContext.TEXT_PART_FACTORY.createCompositeTextPart(CompositeTextPart.class, "").format());
    }

    @Test
    public void testAddTextPart() throws Exception {
        List<TextPart> beforeTestParts = sentence.getChildren();
        Word newWord = (Word) wordSplitChain.build("extraWord");
        beforeTestParts.add(newWord);
        sentence.addTextPart(newWord);
        Assert.assertArrayEquals(beforeTestParts.toArray(), sentence.getChildren().toArray());

        beforeTestParts.add(null);
        sentence.addTextPart(null);
        Assert.assertNotEquals(beforeTestParts.size(), sentence.getChildren().size());
    }

    @Test
    public void testRemoveTextPart_removeExistedOne() throws Exception {
        List<TextPart> parts = sentence.getChildren();
        parts.remove(5);
        sentence.removeTextPart(5);
        Assert.assertArrayEquals(parts.toArray(), sentence.getChildren().toArray());
    }

    @Test
    public void testRemoveTextPart_checkBounds() throws Exception {
        List<TextPart> parts = sentence.getChildren();
        sentence.removeTextPart(-1);
        Assert.assertArrayEquals(parts.toArray(), sentence.getChildren().toArray());

        sentence.removeTextPart(parts.size());
        Assert.assertArrayEquals(parts.toArray(), sentence.getChildren().toArray());
    }

    @Test
    public void testGetChildren() throws Exception {
        List<TextPart> res = sentence.getChildren();
        Assert.assertEquals(34, sentence.getChildren().size());
        sentence = new CompositeTextPart();
        Assert.assertEquals(0, sentence.getChildren().size());
    }

    @Test
    public void testFindWords() throws Exception {
        String test1 =
                "Ever since the release of Java 5, I've been keeping my eyes open for a book that describes what " +
                        "I believe to be the most powerful new feature of Java - generics !?? As a bonus, the second half of this book " +
                        "The new book published by O'Reilly, Java Generics and Collections covers the topic in spades..." +
                        "examines the Java Collections Framework todo? Since the Collections Framework was rewritten to incorporate " +
                        "the use of generics, it makes perfect sense for the authors to spend a reasonable amount of time describing " +
                        "the new interfaces!";

        check(prepareExpectations("Ever", "Java", "I've", "been", "eyes", "open", "book", "that", "what", "most", "todo"), test1, 4);
        check(new TextPart[0], test1, -1);
        check(new TextPart[0], test1, 0);
    }


    private void check(TextPart[] expectation, String text, int length) {
        Assert.assertArrayEquals(expectation, textSplitChain.build(text).findWords(length).toArray());
    }

    private TextPart[] prepareExpectations(String... words) {
        TextPart[] res = new TextPart[words.length];
        Arrays.asList(words).stream().map(this::buildWord).collect(Collectors.toSet()).toArray(res);
        return res;
    }

    private TextPart buildWord(String word) {
        return wordSplitChain.build(word);
    }
}