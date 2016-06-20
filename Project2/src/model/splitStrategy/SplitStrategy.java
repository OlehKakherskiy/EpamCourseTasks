package model.splitStrategy;

import app.GlobalContext;
import model.TextPart;
import model.textPartFactory.TextPartFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class SplitStrategy<E extends TextPart> {

    protected TextPartFactory textPartFactory;

    protected Class<? extends E> splitPartType;

    public SplitStrategy(Class<? extends E> genericType) {
        this();
        this.splitPartType = genericType;
    }

    public SplitStrategy() {
        textPartFactory = (TextPartFactory) GlobalContext.CONTEXT_PARAMS.get(GlobalContext.TEXT_PART_FACTORY_KEY);
    }

    public final List<E> split(String partToSplit) {
        return Arrays.asList(splitForStringParts(partToSplit)).stream().map(this::createNewPart).collect(Collectors.toList());
    }

    protected abstract String[] splitForStringParts(String partToSplit);

    protected E createNewPart(String part) {
        return textPartFactory.newInstance(splitPartType, part);
    }
}