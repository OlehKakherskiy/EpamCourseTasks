package model;

import model.splitStrategy.SplitStrategy;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Text extends CompositeTextPart<Sentence> {

    public Text(String textPart, SplitStrategy<Sentence> splitStrategy) {
        super(textPart, splitStrategy);
    }

    @Override
    protected void resetCaches(Sentence part, boolean removeOrAppend) {
        part.getChildren().forEach(child -> part.resetCaches(child, removeOrAppend));
    }
}
