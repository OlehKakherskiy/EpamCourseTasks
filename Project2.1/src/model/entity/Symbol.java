package model.entity;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Symbol extends TextPart {

    private char c;

    Symbol(char c) {
        this.c = c;
    }

    @Override
    public Set<TextPart> findWords(int length) {
        return Collections.emptySet();
    }

    @Override
    public String format() {
        return "" + c;
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
