package model.splitStrategy;

import app.GlobalContext;
import com.sun.istack.internal.NotNull;
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

    public SplitStrategy(@NotNull Class<? extends E> genericType) {
        textPartFactory = (TextPartFactory) GlobalContext.CONTEXT_PARAMS.get(GlobalContext.TEXT_PART_FACTORY_KEY);
//        if (genericTypes.length == 0)?
//            throw new IllegalArgumentException("generic types, operated by " + getClass().getName() + "can't be empty. " +
//                    "Each split strategy class must know at least 1 type to split input string for");
        this.splitPartType = genericType;
    }


    public final List<E> split(String partToSplit) {
        return Arrays.asList(splitForStringParts(partToSplit)).stream().map(this::createNewPart).collect(Collectors.toList());
    }

    protected abstract String[] splitForStringParts(String partToSplit);

    protected E createNewPart(String part) {
        return textPartFactory.newInstance(splitPartType, part);
    }
}