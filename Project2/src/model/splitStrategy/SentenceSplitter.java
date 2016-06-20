package model.splitStrategy;

import app.GlobalContext;
import model.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * разбиение на предложения
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentenceSplitter extends SplitStrategy<Sentence> {

    private static String delims = (String) GlobalContext.getParam(GlobalContext.TEXT_SPLIT_PATTERN_KEY);

    public SentenceSplitter() {
        super(Sentence.class);
    }

    @Override
    protected String[] splitForStringParts(String partToSplit) {
        StringTokenizer tokenizer = new StringTokenizer(partToSplit, delims, true);
        List<String> result = new ArrayList<>();
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            if (delims.contains(nextToken)) { //this token is [.?!], so add it to the previous sentence.
                result.set(i - 1, result.get(i - 1) + nextToken);
            } else { //this token is not [.?!], so this token is sentence without full stop punctuation marks
                result.add(i++, nextToken);
            }
        }
        return (String[]) result.toArray();
    }
}