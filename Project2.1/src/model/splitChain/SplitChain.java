package model.splitChain;

import model.entity.CompositeTextPart;
import model.entity.TextPart;
import model.entity.TextPartFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class SplitChain {

    protected SplitChain next;

    protected String groupDelimiter;

    protected TextPartFactory factory;

    Class<? extends TextPart> instanceClass;

    public SplitChain(SplitChain next, String groupDelimiter, TextPartFactory factory,
                      Class<? extends TextPart> instanceClass) {
        this.next = next;
        this.groupDelimiter = groupDelimiter;
        this.factory = factory;
        this.instanceClass = instanceClass;
    }

    public final TextPart build(String textPart) {
        if (textPart == null || textPart.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return buildHook(textPart.trim());
    }

    protected TextPart buildHook(String textPart) {
        return (next != null) ? createCompositeElement(createChildren(textPart)) : createLeafElement(textPart);
    }

    public String group(TextPart part) {
        return (next == null) ? "" :
                String.join(groupDelimiter, part.getChildren().stream().map(child -> next.group(child)).collect(Collectors.toList()));
    }

    protected abstract List<String> splitForNextChain(String textPart);

    protected TextPart createLeafElement(String textPart) {
        throw new UnsupportedOperationException(); //TODO: add excep descr.
    }

    private TextPart createCompositeElement(List<TextPart> children) {
        return factory.getUnsharedTextPart((Class<? extends CompositeTextPart>) instanceClass, this, children);
    }

    private List<TextPart> createChildren(String textPart) {
        return splitForNextChain(textPart).stream().map(next::build).collect(Collectors.toList());
    }
}