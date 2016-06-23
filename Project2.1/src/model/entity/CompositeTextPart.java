package model.entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class represents composite text part - part, that can be consisted from different parts. It service
 * full interface implementation, declared in {@link TextPart}. Has own {@link SplitChain} for providing
 * splitting/merging operations, and adding a child operation from String value, e.g.
 * <p>
 * Composite textPart textPart; //built textPart
 * <p>
 * textPart.addTextPart(textPart.getSplitChain().build(text));
 * </p>
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see SplitChain
 */
public class CompositeTextPart extends TextPart {

    /**
     * subparts of this text part
     */
    protected List<TextPart> parts = new ArrayList<>();

    /**
     * build/merge strategy
     */
    protected SplitChain splitChain;

    CompositeTextPart() {
    }

    /**
     * {@inheritDoc}
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
            for (TextPart p : parts) {
                result.addAll(p.findWords(length));
            }
            return result;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param textPart text part, that should be added to the end of current part's children list
     */
    @Override
    public void addTextPart(TextPart textPart) {
        if (textPart != null) {
            parts.add(textPart);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param position specific child's position. Should be >0 AND < children count
     */
    @Override
    public void removeTextPart(int position) {
        if (position >= 0 && position < parts.size()) {
            parts.remove(position);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public List<TextPart> getChildren() {
        return parts.stream().collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * <p>
     * calls {@link SplitChain#group(TextPart)} method
     * </p>
     *
     * @return {@inheritDoc}
     */
    @Override
    public String format() {
        return splitChain.group(this);
    }

    void setSplitChain(SplitChain splitChain) {
        this.splitChain = splitChain;
    }

    public void setParts(List<TextPart> parts) {
        this.parts = parts;
    }

    public SplitChain getSplitChain() {
        return splitChain;
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
}