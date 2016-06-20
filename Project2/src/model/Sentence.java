package model;

import model.splitStrategy.SplitStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Sentence extends CompositeTextPart<SentencePart> {

    private SentenceType type;

    protected Map<Integer, Set<Word>> differentWordsCache = new HashMap<>();

    public Sentence(String textPart, SplitStrategy<SentencePart> splitStrategy) {
        super(textPart, splitStrategy);
        initType();
    }

    @Override
    protected void resetCaches(SentencePart part, boolean removeOrAppend) {
        if (Word.class.isAssignableFrom(part.getClass())) {
            //word was added to the sentence (or word was changed by adding or removing symbols)
            int wordLength = part.getChildren().size();
            clearCache(this.differentWordsCache, wordLength, removeOrAppend);
            if (parent != null) {
                clearCache(parent.differentWordsCache, wordLength, removeOrAppend);
            }
        }
    }

    private void clearCache(Map<Integer, Set<Word>> cache, int wordLength, boolean removeOrAppend) {
        cache.remove(wordLength);
        if (removeOrAppend) {
            cache.remove(wordLength - 1);
        } else {
            cache.remove(wordLength + 1);
        }
    }

    public SentenceType getType() {
        return type;
    }

    private void initType() {
        SentencePart last = parts.get(parts.size() - 1);
        String punctMark = last.formatToString("");
        boolean isQuestion = punctMark.contains("?");
        boolean isExclamatory = punctMark.contains("!");
        if (isQuestion && isExclamatory) {
            type = SentenceType.QUESTION_EXCLAMATORY;
        } else if (isQuestion) {
            type = SentenceType.QUESTION;
        } else if (isExclamatory) {
            type = SentenceType.EXCLAMATORY;
        } else {
            type = SentenceType.NARRATIVE;
        }
    }
}