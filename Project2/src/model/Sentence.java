package model;

import model.splitStrategy.SplitStrategy;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Sentence extends CompositeTextPart<SentencePart> {

    public Sentence(String textPart, SplitStrategy<SentencePart> splitStrategy) {
        super(textPart, splitStrategy);
    }
}
