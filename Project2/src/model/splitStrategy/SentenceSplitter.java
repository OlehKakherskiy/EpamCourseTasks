package model.splitStrategy;

import model.Sentence;
import model.SentencePart;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * разбиение на предложения
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentenceSplitter extends SplitStrategy<Sentence> {

    public SentenceSplitter() {
        super(SentencePart.class);
    }

    @Override
    protected String[] splitForStringParts(String partToSplit) {
        StringTokenizer tokenizer = new StringTokenizer(".?!");
        List<String> result = new ArrayList<>();
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            if (".?!".contains(nextToken)) { //this token is [.?!], so add it to the previous sentence.
                result.set(i - 1, result.get(i - 1) + nextToken);
            } else { //this token is not [.?!], so this token is sentence without full stop punctuation marks
                result.add(i++, nextToken);
            }
        }
        return (String[]) result.toArray();
    }

    @Override
    protected SentencePart createNewPart(String part) {
        return textPartFactory.newInstance(SentencePart.class, part);
    }
}