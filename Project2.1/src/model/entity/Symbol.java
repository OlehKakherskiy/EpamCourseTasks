package model.entity;

import java.util.Collections;
import java.util.Set;

/**
 * Class represents leaf text part - symbol.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Symbol extends TextPart {

    /**
     * internal state of symbol
     */
    private char c;

    Symbol(char c) {
        this.c = c;
    }

    /**
     * returns empty set
     *
     * @param length words length that should be found
     * @return
     */
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
