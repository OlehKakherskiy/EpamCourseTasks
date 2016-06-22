package model.splitChain;

import app.GlobalContext;
import model.entity.CompositeTextPart;
import model.entity.TextPartFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TextSplitChain extends SplitChain {

    private static String delims = (String) GlobalContext.getParam(GlobalContext.TEXT_SPLIT_PATTERN_KEY);

    public TextSplitChain(SplitChain next, String groupDelimiter, TextPartFactory factory, Class<? extends CompositeTextPart> instanceClass) {
        super(next, groupDelimiter, factory, instanceClass);
    }

    @Override
    protected List<String> splitForNextChain(String textPart) {
        if (textPart == null || textPart.trim().length() == 0)
            return Collections.emptyList();
        StringTokenizer tokenizer = new StringTokenizer(textPart, delims, true);
        List<String> buffer = new ArrayList<>();
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken().trim();
            if (delims.contains(nextToken)) { //this token is [.?!], so add it to the previous sentence.
                buffer.set(i - 1, buffer.get(i - 1) + nextToken);
            } else { //this token is not [.?!], so this token is sentence without full stop punctuation marks
                buffer.add(i++, nextToken);
            }
        }
        return buffer;
    }
}
