package model.textPartFactory;

import model.Symbol;
import model.TextPart;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextPartFactory {

    private Map<String, Symbol> symbols = new HashMap<>();

    private Map<Class<? extends TextPart>, Map<String, Object>> classConfigs;

    public <T> T newInstance(Class<T> instanceClass, String textPart) {

        if (instanceClass == Symbol.class) {
            return (T) getSharedTextPart(textPart);
        } else {
            return (T) getSharedTextPart(textPart); //TODO: передать также доп параметры в конструктор
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

    private <T> T getUnsharedTextPart(Class<T> instanceClass, String textPart) {
        return null; //TODO:!!!! not null
    }

    private boolean isValidTextPart(String textPart) {
        return textPart != null && textPart.isEmpty();
    }
}
