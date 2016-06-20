package model;

import java.util.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class TextPart<T extends TextPart> {

    protected TextPart parent;

    protected Map<Integer, Set<Word>> differentWordsCache = new HashMap<>();

    public abstract String formatToString(String delimiter);

    public final Set<Word> findWords(int length) {
        if (length <= 0) {
            return Collections.emptySet();
        } else {
            return differentWordsCache.containsKey(length) ? differentWordsCache.get(length) : findAndAddToCacheResult(length);
        }
    }

    protected abstract void addTextPart(T textPart);

    protected abstract void removeTextPart(int position);

    protected abstract List<T> getChildren();

    /**
     * @param part
     * @param removeOrAppend true - append operation, false - removeOperation
     */
    protected void resetCaches(T part, boolean removeOrAppend) {
        return;
    }

    protected abstract Set<Word> findWordsHook(int length);

    private Set<Word> findAndAddToCacheResult(int length) {
        Set<Word> result = findWordsHook(length);
        differentWordsCache.put(length, result);
        return result;
    }

    public void setParent(TextPart parent) {
        this.parent = parent;
    }

}