package model.entity;

import model.splitChain.SplitChain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Word extends CompositeTextPart {

    Word(List<TextPart> parts, SplitChain splitChain) {
        super(parts, splitChain);
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