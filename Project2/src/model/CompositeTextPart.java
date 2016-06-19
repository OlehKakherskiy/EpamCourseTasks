package model;

import model.splitStrategy.SplitStrategy;

import java.util.Collections;
import java.util.List;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class CompositeTextPart<K extends TextPart> extends TextPart {

    protected List<K> parts;

    protected SplitStrategy<K> splitStrategy;

    public CompositeTextPart(String textPart, SplitStrategy<K> splitStrategy) {
        super();
        this.splitStrategy = splitStrategy;
        parts = (textPart == null || textPart.isEmpty()) ?
                Collections.emptyList() :
                splitForParts(textPart);
    }

    @Override
    public String formatToString(String delimiter) {
        StringBuilder builder = new StringBuilder();
        parts.stream().forEach((t) -> builder.append(t).append(delimiter));
        return builder.toString();
    }

    protected List<K> splitForParts(String textPart) {
        return splitStrategy.split(textPart);
    }
}