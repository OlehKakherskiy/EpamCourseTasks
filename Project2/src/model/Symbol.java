package model;

import app.GlobalContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Symbol extends TextPart {

    private char c;

    public Symbol(char c) {
        this.c = c;
    }

    @Override
    public String formatToString(String delimiter) {
        return "" + c;
    }

    @Override
    protected void addTextPart(TextPart textPart) {
        throw new UnsupportedOperationException((String) GlobalContext.getParam(GlobalContext.UNSUPPORTED_OPERATION_TEXT_KEY));
    }

    @Override
    protected Set<Word> findWordsHook(int length) {
        return Collections.emptySet();
    }

    @Override
    protected void removeTextPart(int position) {
        throw new UnsupportedOperationException((String) GlobalContext.getParam(GlobalContext.UNSUPPORTED_OPERATION_TEXT_KEY));
    }

    @Override
    protected List getChildren() {
        throw new UnsupportedOperationException((String) GlobalContext.getParam(GlobalContext.UNSUPPORTED_OPERATION_TEXT_KEY));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;

        Symbol symbol = (Symbol) o;

        return c == symbol.c;
    }

    @Override
    public int hashCode() {
        return (int) c;
    }
}
