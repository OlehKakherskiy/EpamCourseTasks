package model.splitChain;

import app.GlobalContext;
import model.entity.SplitChain;
import model.entity.TextPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentenceSplitChain extends SplitChain {

    /**
     * regexp that indicates punctuation marks
     */
    private static final Pattern SPLIT_REGEXP =
            Pattern.compile((String) GlobalContext.getParam(GlobalContext.SENTENCE_PART_SPLIT_REGEXP_KEY));


    public SentenceSplitChain(SplitChain next, String groupDelimiter, Class<? extends TextPart> instanceClass) {
        super(next, groupDelimiter, instanceClass);
    }

    /**
     * Splits for words. Handle specific situations, where token can consist from word and punctuation marks
     * - divides them for two tokens.
     *
     * @param textPart string representation of all children of this {@link TextPart}
     * @return list of words
     */
    @Override
    protected List<String> splitForNextChain(String textPart) {
        if (textPart == null || textPart.trim().length() == 0)
            return Collections.emptyList();
        List<String> buffer = Arrays.asList(textPart.split(" +")).stream().map(String::trim).filter(s -> s.length() > 0).collect(Collectors.toList());
        List<String> result = new ArrayList<>(buffer.size());
        for (String elem : buffer) {

            //if word consists from 1 char or word - is punctuation mark (can have several chars), adds to result
            StringBuilder current = new StringBuilder(elem);
            if (current.length() == 1 || SPLIT_REGEXP.matcher(current.toString()).matches()) {
                result.add(current.toString());
                continue;
            }

            //check if some punctuation mark is the start of the word, e.g. "+word". if true, remove this mark from the word and make
            //them two tokens
            if (SPLIT_REGEXP.pattern().indexOf(current.charAt(0)) != -1) {
                result.add("" + current.charAt(0));
                current.deleteCharAt(0);
            }

            StringBuilder stopMarkBuilder = new StringBuilder();
            char lastSymbol = current.charAt(current.length() - 1);
            while (SPLIT_REGEXP.pattern().indexOf(lastSymbol) != -1) {
                stopMarkBuilder.append(lastSymbol);
                current.deleteCharAt(current.length() - 1);
                lastSymbol = current.charAt(current.length() - 1);
            }
            result.add(current.toString());
            if (stopMarkBuilder.length() != 0) {
                result.add(stopMarkBuilder.reverse().toString());
            }
            //TODO: refactoring

        }
        buffer.clear();

        return result;
    }
}
