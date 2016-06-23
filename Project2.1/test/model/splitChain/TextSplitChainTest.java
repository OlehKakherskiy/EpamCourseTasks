package model.splitChain;

import app.GlobalContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextSplitChainTest {

    static TextSplitChain splitChain;

    @BeforeClass
    public static void init() {
        splitChain = (TextSplitChain) GlobalContext.getParam(GlobalContext.TEXT_SPLIT_STRATEGY_KEY);
    }

    @Test
    public void testSplitForNextChain() throws Exception {
        String test1 =
                "Ever since the release of Java 5, I've been keeping my eyes open for a book that describes what " +
                        "I believe to be the most powerful new feature of Java - generics !?? The new book published by O'Reilly, " +
                        "Java Generics and Collections covers the topic in spades. As a bonus, the second half of this book " +
                        "examines the Java Collections Framework...Since the Collections Framework was rewritten to incorporate " +
                        "the use of generics, it makes perfect sense for the authors to spend a reasonable amount of time describing " +
                        "the new interfaces.";

        String[] expectation1 = {
                "Ever since the release of Java 5, I've been keeping my eyes open for a book that describes what I believe to be the most powerful new feature of Java - generics!??",
                "The new book published by O'Reilly, Java Generics and Collections covers the topic in spades.",
                "As a bonus, the second half of this book examines the Java Collections Framework...",
                "Since the Collections Framework was rewritten to incorporate the use of generics, it makes perfect sense for the authors to spend a reasonable amount of time describing the new interfaces."};
        check(test1, expectation1);
        check("", new String[0]);
        check(null, new String[0]);
    }

    private void check(String test, String[] expectations) {
        Assert.assertArrayEquals(expectations, splitChain.splitForNextChain(test).toArray());
    }
}