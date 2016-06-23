package model.splitChain;

import app.GlobalContext;
import model.entity.SplitChain;
import model.entity.TextPart;

import java.util.Collections;
import java.util.List;

/**
 * Class encapsulates logic of building shared text parts
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SymbolSplit extends SplitChain {


    public SymbolSplit(String groupDelimiter, Class<? extends TextPart> instanceClass) {
        super(null, groupDelimiter, instanceClass);
    }

    /**
     * This is the last chain, so don't splits for parts
     *
     * @param textPart string representation of all children of this {@link TextPart}
     * @return {@link Collections#EMPTY_LIST}
     */
    @Override
    protected List<String> splitForNextChain(String textPart) {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates new shared part using factory
     * </p>
     *
     * @param textPart string representation of text, that should be reformatted to {@link TextPart} hierarchy
     * @return {@inheritDoc}
     */
    @Override
    protected TextPart buildHook(String textPart) {
        return GlobalContext.TEXT_PART_FACTORY.getSharedTextPart(textPart);
    }
}
