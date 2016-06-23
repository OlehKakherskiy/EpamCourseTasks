package model.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class SplitChain {

    protected SplitChain next;

    protected String groupDelimiter;

    Class<? extends TextPart> instanceClass;

    public SplitChain(SplitChain next, String groupDelimiter,
                      Class<? extends TextPart> instanceClass) {
        this.next = next;
        this.groupDelimiter = groupDelimiter;
        this.instanceClass = instanceClass;
    }

    public final TextPart build(String textPart) {
        if (textPart == null || textPart.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return buildHook(textPart.trim());
    }

    protected TextPart buildHook(String textPart) {
        return createElement(createChildren(textPart.trim()), (Class<? extends CompositeTextPart>) instanceClass);
    }

    public String group(TextPart part) {
        return (next == null) ? "" :
                String.join(groupDelimiter, part.getChildren().stream().map(child -> next.group(child)).collect(Collectors.toList()));
    }

    protected abstract List<String> splitForNextChain(String textPart);

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

    private List<TextPart> createChildren(String textPart) {
        return splitForNextChain(textPart).stream().map(next::build).collect(Collectors.toList());
    }
}