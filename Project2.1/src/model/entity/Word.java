package model.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Word extends CompositeTextPart {

    Word() {
    }

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