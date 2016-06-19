package model;

import model.splitStrategy.SplitStrategy;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Word extends SentencePart<Symbol> {

    public Word(String textPart, SplitStrategy<Symbol> splitStrategy) {
        super(textPart, splitStrategy);
    }
}
