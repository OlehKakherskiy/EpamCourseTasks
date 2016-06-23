package model.splitChain;

import app.GlobalContext;
import model.entity.SplitChain;
import model.entity.TextPart;

import java.util.Collections;
import java.util.List;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SymbolSplit extends SplitChain {

    public SymbolSplit(String groupDelimiter, Class<? extends TextPart> instanceClass) {
        super(null, groupDelimiter, instanceClass);
    }

    @Override
    protected List<String> splitForNextChain(String textPart) {
        return Collections.emptyList();
    }

    @Override
    protected TextPart buildHook(String textPart) {
        return GlobalContext.TEXT_PART_FACTORY.getSharedTextPart(textPart);
    }
}
