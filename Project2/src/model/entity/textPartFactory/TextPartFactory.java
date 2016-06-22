package model.entity.textPartFactory;

import model.Symbol;
import model.TextPart;
import model.splitStrategy.SplitStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextPartFactory {

    private Map<String, Symbol> symbols = new HashMap<>();

    private Map<Class<? extends TextPart>, Class<? extends SplitStrategy>> textPartConfigs;

    public <T extends TextPart> T newInstance(Class<? extends TextPart> instanceClass, String textPart) {
        if (instanceClass == null)
            return null;
        if (instanceClass == Symbol.class) {
            return (T) getSharedTextPart(textPart);
        } else {
            return (T) getUnsharedTextPart(instanceClass, textPart);
        }
    }

    private Symbol getSharedTextPart(String textPart) {
        Symbol res = null;
        if ((res = symbols.get(textPart)) == null) {
            res = new Symbol(textPart.charAt(0));
            symbols.put(textPart, res);
        }
        return res;
    }

    private <T extends TextPart> T getUnsharedTextPart(Class<T> instanceClass, String textPart) {
        try {
            Constructor<? extends TextPart> constructor = instanceClass.getConstructor(String.class, SplitStrategy.class);
            Class<? extends SplitStrategy> splitStrategy = textPartConfigs.get(instanceClass);
            if (splitStrategy == null) {
                throw new IllegalArgumentException("split strategy isn't instantiated for class " + instanceClass.getName());
            }
            return (T) constructor.newInstance(textPart, textPartConfigs.get(instanceClass).newInstance());
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

    public void setTextPartConfigs(Map<Class<? extends TextPart>, Class<? extends SplitStrategy>> textPartConfigs) {
        this.textPartConfigs = textPartConfigs;
    }

    private boolean isValidTextPart(String textPart) {
        return textPart != null && textPart.isEmpty();
    }
}
