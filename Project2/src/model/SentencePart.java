package model;

import model.splitStrategy.SplitStrategy;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class SentencePart<T extends TextPart> extends CompositeTextPart<T> {

    public SentencePart(String textPart, SplitStrategy<T> splitStrategy) {
        super(textPart, splitStrategy);
    }
}
