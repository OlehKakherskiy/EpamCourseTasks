package model.splitChain;

import model.entity.TextPart;
import model.entity.TextPartFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SymbolSplit extends SplitChain {

    public SymbolSplit(String groupDelimiter, TextPartFactory factory, Class<? extends TextPart> instanceClass) {
        super(null, groupDelimiter, factory, instanceClass);
    }

    @Override
    protected List<String> splitForNextChain(String textPart) {
        return Collections.emptyList();
    }

    @Override
    protected TextPart createLeafElement(String textPart) {
        return factory.getSharedTextPart(textPart);
    }
}
