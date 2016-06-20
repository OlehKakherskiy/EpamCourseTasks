package app;

import model.splitStrategy.SentencePartSplitter;
import model.splitStrategy.SentenceSplitter;
import model.splitStrategy.SplitStrategy;
import model.splitStrategy.SymbolSplitter;

import java.util.StringTokenizer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {

        String str = "fgjodfijgiodjfg doifgdoifgnuenrengk dfdgmdfgkmgk,oleh";
        StringTokenizer tokenizer = new StringTokenizer(str, " ,", true);

        SplitStrategy strategy1 = new SentenceSplitter();
        SplitStrategy strategy2 = new SentencePartSplitter();
        SplitStrategy strategy3 = new SymbolSplitter();


    }
}