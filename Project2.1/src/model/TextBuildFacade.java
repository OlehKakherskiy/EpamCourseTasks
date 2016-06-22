package model;

import model.entity.TextPart;
import model.splitChain.SplitChain;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextBuildFacade {

    private SplitChain textBuildStrategy;

    public TextBuildFacade(SplitChain textBuildStrategy) {
        this.textBuildStrategy = textBuildStrategy;
    }

    public Set<String> findWords(String fromString, int length) {
        TextPart text = textBuildStrategy.build(fromString);
        return text.findWords(length).stream().map(TextPart::format).collect(Collectors.toSet());
    }

    public void addPart(TextPart addTo, TextPart part) {
        if (addTo != null && part != null) {
            addTo.addTextPart(part);
        }
    }

    public void removeTextPart(TextPart removeFrom, int index) {
        if (removeFrom == null)
            return;
        removeFrom.removeTextPart(index);
    }

    public String formatToString(TextPart part) {
        return textBuildStrategy.group(part);
    }
}
