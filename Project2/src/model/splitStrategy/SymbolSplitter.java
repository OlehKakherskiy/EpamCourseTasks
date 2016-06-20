package model.splitStrategy;

import model.Symbol;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SymbolSplitter extends SplitStrategy<Symbol> {

    public SymbolSplitter() {
        super(Symbol.class);
    }

    @Override
    protected String[] splitForStringParts(String partToSplit) {
        return partToSplit.split("");
    }
}