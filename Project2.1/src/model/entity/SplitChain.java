package model.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class represents root of chain responsibility hierarchy of text part builders.
 * Each one has link to the next chain, so that textPart can be build with specific structure
 * (e.g. text can consists from paragraphs, each paragraph can consists from sentences, sentences - words,
 * words - symbols). The text structure can be dynamically reconfigured. Also each chain can reformat
 * part with tree structure to string representation of this structure (uses {@link #groupDelimiter} for
 * grouping to string. Each chain can create a text part of specific type, represented in
 * {@link #instanceClass} field.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class SplitChain {

    /**
     * next chain. can be null, if this chain builds leaf elements
     */
    protected SplitChain next;

    /**
     * delimiter between text parts, while formatting as a string
     */
    protected String groupDelimiter;

    /**
     * text part's type, that this chain creates
     */
    Class<? extends TextPart> instanceClass;

    /**
     * inits all fields
     *
     * @param next           next chain
     * @param groupDelimiter delimiter between text parts
     * @param instanceClass  text part's type, that this chain creates
     */
    public SplitChain(SplitChain next, String groupDelimiter,
                      Class<? extends TextPart> instanceClass) {
        this.next = next;
        this.groupDelimiter = groupDelimiter;
        this.instanceClass = instanceClass;
    }

    /**
     * builds new text part, which type is {@link #instanceClass}
     *
     * @param textPart string representation of this text part,
     * @return instance of {@link #instanceClass}, which internal structure of elements of text part parameter
     * is quite similar to input param
     * @throws IllegalArgumentException if param is null or empty after trimming
     */
    public final TextPart build(String textPart) {
        if (textPart == null || textPart.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return buildHook(textPart.trim());
    }

    /**
     * Builds {@link TextPart} structure from specific string parameter.
     * <p>
     * Should be redefined, if this chain creates TextPart, that is not a subclass of {@link CompositeTextPart}
     * </p>
     *
     * @param textPart string representation of text, that should be reformatted to {@link TextPart} hierarchy
     * @return instance of {@link #instanceClass}, which internal structure of elements of text part parameter
     * is quite similar to input param
     */
    protected TextPart buildHook(String textPart) {
        return createElement(createChildren(textPart.trim()), (Class<? extends CompositeTextPart>) instanceClass);
    }

    /**
     * builds new instance of
     *
     * @param children      this text part's children parts
     * @param instanceClass link to {@link #instanceClass}
     * @return instance of {@link #instanceClass}, that has consistent state and ready for using
     */
    private <T extends CompositeTextPart> T createElement(List<TextPart> children, Class<T> instanceClass) {
        try {
            T instance = instanceClass.newInstance();
            instance.setParts(children);
            instance.setSplitChain(this);
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * creates children parts for new instantiated part, children parts are created using {@link #next}.
     *
     * @param textPart text representation of all child parts
     * @return {@link TextPart} structure of input parameter
     */
    private List<TextPart> createChildren(String textPart) {
        return splitForNextChain(textPart).stream().map(next::build).collect(Collectors.toList());
    }

    /**
     * Strategy of dividing textPart to tokens, each of that is a string representation of text part,
     * that is a child of this one.
     *
     * @param textPart string representation of all children of this {@link TextPart}
     * @return List of tokens, this part's children will be build from these tokens
     */
    protected abstract List<String> splitForNextChain(String textPart);

    /**
     * joins all children text representation with {@link #groupDelimiter}
     *
     * @param part {@link TextPart} instance, which hierarchical representation will be represent as text.
     *             If null - returns empty string
     * @return text representation of param object
     */
    public String group(TextPart part) {
        return (next == null) ? "" :
                String.join(groupDelimiter, part.getChildren().stream().map(child -> next.group(child)).collect(Collectors.toList()));
    }
}