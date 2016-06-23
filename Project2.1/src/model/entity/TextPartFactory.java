package model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextPartFactory {

    /**
     * shared parts
     */
    private Map<String, Symbol> symbols = new HashMap<>();

    /**
     * build strategy for each composite part type
     */
    private Map<Class<? extends CompositeTextPart>, SplitChain> splitStrategies;

    /**
     * inits {@link #splitStrategies}
     *
     * @param splitStrategies build strategy for each composite part type
     */
    public TextPartFactory(Map<Class<? extends CompositeTextPart>, SplitChain> splitStrategies) {
        this.splitStrategies = splitStrategies;
    }

    /**
     * returns {@link Symbol} from cache or create new one, that string representation is similar
     * to input param
     *
     * @param textPart
     * @return
     */
    public Symbol getSharedTextPart(String textPart) {
        if (!isValidTextPart(textPart))
            return null;
        textPart = textPart.trim();
        Symbol res = null;
        if ((res = symbols.get(textPart)) == null) {
            res = new Symbol(textPart.charAt(0));
            symbols.put(textPart, res);
        }
        return res;
    }

    /**
     * builds new {@link CompositeTextPart} instance from specific String.
     *
     * @param instanceClass class of new instance
     * @param textPart      text representation of new instance's hierarchy
     * @return new instance of instanceClass param with a structure, text representation
     * is in specific textPart param
     * @throws IllegalArgumentException if textPart param is null or empty
     * @throws RuntimeException         if for instanceClass param there is no build strategy
     */
    public <T extends CompositeTextPart> T createCompositeTextPart(Class<T> instanceClass, String textPart) {
        if (!isValidTextPart(textPart))
            throw new IllegalArgumentException();
        SplitChain chain = splitStrategies.get(instanceClass);

        if (chain == null) {
            throw new RuntimeException("No split strategy for class " + instanceClass.getName());
        }
        return (T) chain.build(textPart);

    }

    private boolean isValidTextPart(String textPart) {
        return textPart != null && !textPart.trim().isEmpty();
    }
}
