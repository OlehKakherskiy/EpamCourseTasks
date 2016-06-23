package model.entity;

import app.GlobalContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Class represents sentence composite object, that consists from other textParts - words and punctuation marks
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Sentence extends CompositeTextPart {

    //regexp that indicates punctuation marks
    private static final Pattern SPLIT_REGEXP =
            Pattern.compile((String) GlobalContext.getParam(GlobalContext.SENTENCE_PART_SPLIT_REGEXP_KEY));

    Sentence() {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Checks whether the sentence has questioning marks and finds all words with
     * target length
     * </p>
     *
     * @param length words length that should be found
     * @return {@inheritDoc}
     */
    @Override
    public Set<TextPart> findWords(int length) {
        //checks whether last word in sentence(punctuation mark) contains question mark
        if (parts.size() == 0 || !parts.get(parts.size() - 1).format().contains("?")) {
            return Collections.EMPTY_SET;
        } else {
            Set<TextPart> res = new HashSet<>();
            for (TextPart part : parts) { //for all Word objects calls findWord method and reduces result
                if (!SPLIT_REGEXP.matcher(part.format()).matches()) { //punctuation marks don't take part
                    res.addAll(part.findWords(length));
                }
            }
            return res;
        }
    }
}
