package model;

import model.splitStrategy.SplitStrategy;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class PunctuationMark extends SentencePart<Symbol> {

    public PunctuationMark(String textPart, SplitStrategy<Symbol> splitStrategy) {
        super(textPart, splitStrategy);
    }

    @Override
    protected void resetCaches(Symbol part, boolean removeOrAppend) {
        return;
    }

}