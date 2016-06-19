package model;

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
}
