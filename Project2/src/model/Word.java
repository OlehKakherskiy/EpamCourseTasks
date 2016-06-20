package model;

import model.splitStrategy.SplitStrategy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Word extends SentencePart<Symbol> {

    public Word(String textPart, SplitStrategy<Symbol> splitStrategy) {
        super(textPart, splitStrategy);
    }

    @Override
    protected Set<Word> findWordsHook(int length) {
        Set<Word> result = new HashSet<>();
        if (parts.size() == length) {
            result.add(this);
        }
        return result;
    }

    @Override
    protected void resetCaches(Symbol part, boolean removeOrAppend) {
        if (parent != null) {
            parent.resetCaches(this, removeOrAppend);
        }
    }
}