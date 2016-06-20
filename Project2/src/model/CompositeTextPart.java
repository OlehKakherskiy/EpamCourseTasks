package model;

import model.splitStrategy.SplitStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class CompositeTextPart<K extends TextPart> extends TextPart<K> {

    protected List<K> parts;

    protected SplitStrategy<K> splitStrategy;

    public CompositeTextPart(String textPart, SplitStrategy<K> splitStrategy) {
        super();
        this.splitStrategy = splitStrategy;
        parts = (textPart == null || textPart.isEmpty()) ? new ArrayList<>() : splitForParts(textPart);
        for (K part : parts) { //TODO:!!!!!! тут мб null
            System.out.println(this);
            part.setParent(this);
        }
    }

    @Override
    public String formatToString(String delimiter) {
        StringBuilder builder = new StringBuilder();
        parts.stream().forEach((t) -> builder.append(t).append(delimiter));
        return builder.toString();
    }

    @Override
    protected Set<Word> findWordsHook(int length) {
        Set<Word> result = new HashSet<>();
        for (K part : parts) {
            result.addAll(part.findWords(length));
        }
        return result;
    }

    @Override
    protected void addTextPart(K textPart) {
        if (textPart != null) {
            parts.add(textPart);
            resetCaches(textPart, true);
        }
    }

    @Override
    protected void removeTextPart(int position) {
        if (position >= 0 && position < parts.size()) {
            resetCaches(parts.remove(position), false);
        }
    }

    @Override
    protected List<K> getChildren() {
        return parts.stream().collect(Collectors.toList());
    }

    protected List<K> splitForParts(String textPart) {
        return splitStrategy.split(textPart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeTextPart)) return false;

        CompositeTextPart<?> that = (CompositeTextPart<?>) o;

        return parts.equals(that.parts);

    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }
}