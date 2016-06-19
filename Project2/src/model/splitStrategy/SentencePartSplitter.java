package model.splitStrategy;

import com.sun.istack.internal.NotNull;
import model.SentencePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * разбиение на части предложения
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SentencePartSplitter extends SplitStrategy<SentencePart> {


    public SentencePartSplitter(@NotNull Class<? extends SentencePart> genericType) {
        super(genericType);
    }

    @Override
    protected String[] splitForStringParts(String partToSplit) {
        List<String> buffer = Arrays.asList(partToSplit.split(" "));
        List<String> result = new ArrayList<>(buffer.size());
        for (int i = 0; i < buffer.size(); i++) {
            String current = buffer.get(i);
            //check if some punctuation mark is the start of the word, e.g. "+word". if true, remove this mark from the word and make
            //them two tokens
            if (",.!?[]():;{}@#$%^&*-_+=/<>".indexOf(current.charAt(0)) != -1) {
                appendToEnd(i, "" + current.charAt(0), result);
                current = current.substring(1);
            }
            if (",.!?[]():;{}@#$%^&*-_+=/<>".indexOf(current.charAt(current.length() - 1)) != -1) {
                //TODO: может быть несколько знаков препинания в конце слова, тип троеточие
                appendToEnd(i, current.substring(0, current.length() - 1), result);
                appendToEnd(i + 1, "" + current.charAt(current.length() - 1), result);
            }
        }
        return (String[]) result.toArray();
    }

    @Override
    protected SentencePart createNewPart(String part) {
        return null;
    }

    private void appendToEnd(int fromPos, String elem, List<String> list) {
        ListIterator<String> listIterator = list.listIterator(fromPos);
        while (listIterator.hasNext()) {
            if (listIterator.next() == null) {
                break;
            }
        }
        listIterator.add(elem);
    }
}