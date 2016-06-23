package model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextPartFactory {

    private Map<String, Symbol> symbols = new HashMap<>();

    private Map<Class<? extends CompositeTextPart>, SplitChain> splitStrategies;

    public TextPartFactory(Map<Class<? extends CompositeTextPart>, SplitChain> splitStrategies) {
        this.splitStrategies = splitStrategies;
    }

    public Symbol getSharedTextPart(String textPart) {
        Symbol res = null;
        if ((res = symbols.get(textPart)) == null) {
            res = new Symbol(textPart.charAt(0));
            symbols.put(textPart, res);
        }
        return res;
    }

    public <T extends CompositeTextPart> T createCompositeTextPart(Class<T> instanceClass, String textPart) {
        if (textPart == null || textPart.trim().isEmpty())
            throw new IllegalArgumentException();
        SplitChain chain = splitStrategies.get(instanceClass);

        if (chain == null) {
            throw new RuntimeException("No split strategy for class " + instanceClass.getName());
        }
        return (T) chain.build(textPart);

    }

    private boolean isValidTextPart(String textPart) {
        return textPart != null && textPart.isEmpty();
    }
}
