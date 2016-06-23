package app;

import model.entity.*;
import model.splitChain.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class encapsulates configs and global objects
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class GlobalContext {

    public static final Properties CONTEXT_PARAMS = new Properties();

    public static TextPartFactory TEXT_PART_FACTORY;

    public static final String SENTENCE_PART_SPLIT_REGEXP_KEY = "sentencePartSplitRegexp";

    public static final String TEXT_SPLIT_STRATEGY_KEY = "textSplitStrategy";

    public static final String SENTENCE_SPLIT_STRATEGY_KEY = "sentenceSplitStrategy";

    public static final String WORD_SPLIT_STRATEGY_KEY = "wordSplitStrategy";

    public static final String UNSUPPORTED_OPERATION_TEXT_KEY = "unsupportedOperationException";

    public static final String TEXT_SPLIT_PATTERN_KEY = "splitSentenceTokenizer";

    public static final String INPUT_MESSAGE_KEY = "inputMessage";

    public static final String INPUT_LENGTH_KEY = "inputLength";

    public static final String FILE_NOT_FOUND_KEY = "fileNotFound";


    public static SplitChain buildChains;

    static {

        initConfigs();

        SymbolSplit symbolSplit = new SymbolSplit("", Symbol.class);
        WordSplitChain wordSplitChain = new WordSplitChain(symbolSplit, "", Word.class);
        SentenceSplitChain sentenceSplitChain = new SentenceSplitChain(wordSplitChain, " ", Sentence.class);
        buildChains = new TextSplitChain(sentenceSplitChain, " ", CompositeTextPart.class);

        CONTEXT_PARAMS.put(TEXT_SPLIT_STRATEGY_KEY, buildChains);
        CONTEXT_PARAMS.put(SENTENCE_SPLIT_STRATEGY_KEY, sentenceSplitChain);
        CONTEXT_PARAMS.put(WORD_SPLIT_STRATEGY_KEY, wordSplitChain);


        Map<Class<? extends CompositeTextPart>, SplitChain> configs = new HashMap<>();
        configs.put(CompositeTextPart.class, buildChains);
        configs.put(Sentence.class, (SplitChain) GlobalContext.getParam(GlobalContext.SENTENCE_SPLIT_STRATEGY_KEY));
        configs.put(Word.class, (SplitChain) GlobalContext.getParam(GlobalContext.WORD_SPLIT_STRATEGY_KEY));
        TEXT_PART_FACTORY = new TextPartFactory(configs);
    }

    public static Object getParam(String key) {
        return CONTEXT_PARAMS.get(key);
    }

    public static void initConfigs() {
        try (FileInputStream fis = new FileInputStream("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project2.1\\resources\\config.properties")) {
            CONTEXT_PARAMS.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
