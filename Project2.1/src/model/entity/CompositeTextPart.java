package model.entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class represents composite text part - part, that can be consisted from different parts. It service
 * full interface implementation, declared in {@link TextPart}.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CompositeTextPart extends TextPart {

    protected List<TextPart> parts = new ArrayList<>();

    protected SplitChain splitChain;

    CompositeTextPart() {
    }

    @Override
    public Set<TextPart> findWords(int length) {
        if (length <= 0) {
            return Collections.EMPTY_SET;
        } else {
            Set<TextPart> result = new HashSet<>();
            for (TextPart p : parts) {
                result.addAll(p.findWords(length));
            }
            return result;
        }
    }

    @Override
    public void addTextPart(TextPart textPart) {
        if (textPart != null) {
            parts.add(textPart);
        }
    }

    @Override
    public void removeTextPart(int position) {
        if (position >= 0 && position < parts.size()) {
            parts.remove(position);
        }
    }

    @Override
    public List<TextPart> getChildren() {
        return parts.stream().collect(Collectors.toList());
    }

    @Override
    public String format() {
        return splitChain.group(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeTextPart)) return false;

        CompositeTextPart that = (CompositeTextPart) o;

        return parts.equals(that.parts);

    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }

    public void setSplitChain(SplitChain splitChain) {
        this.splitChain = splitChain;
    }

    public void setParts(List<TextPart> parts) {
        this.parts = parts;
    }
}