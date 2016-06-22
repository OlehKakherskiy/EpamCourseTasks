package model.splitChain;

import model.entity.TextPart;
import model.entity.TextPartFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class WordSplitChain extends SplitChain {

    public WordSplitChain(SplitChain next, String groupDelimiter, TextPartFactory factory,
                          Class<? extends TextPart> instanceClass) {
        super(next, groupDelimiter, factory, instanceClass);
    }

    @Override
    protected List<String> splitForNextChain(String textPart) {
        return (textPart == null || textPart.trim().isEmpty()) ? Collections.EMPTY_LIST : Arrays.asList(textPart.trim().split(""));
    }

    @Override
    public String group(TextPart part) {
        return String.join(groupDelimiter, part.getChildren().stream().map(TextPart::format).collect(Collectors.toList()));
    }
}