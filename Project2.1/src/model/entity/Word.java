package model.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents word composite object, that consists from other textParts - symbols. Punctuation marks
 * also can be represented as objects of this type
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Word extends CompositeTextPart {

    Word() {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Checks whether this word's length is similar to target length
     * </p>
     *
     * @param length words length that should be found
     * @return {@inheritDoc}
     */
    @Override
    public Set<TextPart> findWords(int length) {
        if (length <= 0) {
            return Collections.EMPTY_SET;
        } else {
            Set<TextPart> result = new HashSet<>();
            if (splitChain.group(this).length() == length) {
                result.add(this);
            }
            return result;
        }
    }

    @Override
    public String toString() {
        return this.format();
    }
}