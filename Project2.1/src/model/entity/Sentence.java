package model.entity;

import app.GlobalContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Sentence extends CompositeTextPart {

    Sentence() {
    }

    private static final Pattern punctuationMarkPattern =
            Pattern.compile((String) GlobalContext.getParam(GlobalContext.SENTENCE_PART_SPLIT_REGEXP_KEY));

    @Override
    public Set<TextPart> findWords(int length) {
        if (parts.size() == 0 || !parts.get(parts.size() - 1).format().contains("?")) {
            return Collections.EMPTY_SET;
        } else {
            Set<TextPart> res = new HashSet<>();
            for (TextPart part : parts) {
                res.addAll(part.findWords(length));
            }
            return res;
        }
    }
}
