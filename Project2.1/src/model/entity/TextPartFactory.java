package model.entity;

import model.splitChain.SplitChain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextPartFactory {

    private Map<String, Symbol> symbols = new HashMap<>();

    public Symbol getSharedTextPart(String textPart) {
        Symbol res = null;
        if ((res = symbols.get(textPart)) == null) {
            res = new Symbol(textPart.charAt(0));
            symbols.put(textPart, res);
        }
        return res;
    }

    public CompositeTextPart getUnsharedTextPart(Class<? extends CompositeTextPart> instanceClass,
                                                 SplitChain chain, List<TextPart> children) {
        try {
            Constructor<? extends TextPart> constructor = instanceClass.getDeclaredConstructor(List.class, SplitChain.class);
            if (chain == null) {
                throw new IllegalArgumentException("split strategy isn't instantiated for class " + instanceClass.getName());
            }
            return (CompositeTextPart) constructor.newInstance(children, chain);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidTextPart(String textPart) {
        return textPart != null && textPart.isEmpty();
    }
}
