package model.splitStrategy;

import com.sun.istack.internal.NotNull;
import model.Symbol;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SymbolSplitter extends SplitStrategy<Symbol> {

    public SymbolSplitter(@NotNull Class<Symbol> genericType) {
        super(genericType);
    }

    @Override
    protected String[] splitForStringParts(String partToSplit) {
        return partToSplit.split("");
    }
}