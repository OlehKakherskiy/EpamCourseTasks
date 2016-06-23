package model.splitChain;

import model.entity.SplitChain;
import model.entity.TextPart;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class WordSplitChain extends SplitChain {


    public WordSplitChain(SplitChain next, String groupDelimiter, Class<? extends TextPart> instanceClass) {
        super(next, groupDelimiter, instanceClass);
    }

    /**
     * {@inheritDoc}
     * <p>
     * splits textPart parameter for symbols.
     * </p>
     *
     * @param textPart string representation of all children of this {@link TextPart}. If null or empty
     *                 after trimming - returns {@link Collections#EMPTY_LIST}
     * @return {@link Collections#EMPTY_LIST} or string, consisting from 1 letter from textPart param
     */
    @Override
    protected List<String> splitForNextChain(String textPart) {
        return (textPart == null || textPart.trim().isEmpty()) ? Collections.EMPTY_LIST : Arrays.asList(textPart.trim().split(""));
    }

    /**
     * groups all letters to string.
     *
     * @param part {@link TextPart} instance, which hierarchical representation will be represent as text.
     *             If null - returns empty string
     * @return {@inheritDoc}
     */
    @Override
    public String group(TextPart part) {
        return String.join(groupDelimiter, part.getChildren().stream().map(TextPart::format).collect(Collectors.toList()));
    }
}