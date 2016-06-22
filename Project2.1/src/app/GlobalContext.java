package app;

import model.TextBuildFacade;
import model.entity.CompositeTextPart;
import model.entity.Sentence;
import model.entity.Symbol;
import model.entity.Word;
import model.entity.TextPartFactory;
import model.splitChain.*;

import java.io.FileInputStream;
import java.io.IOException;
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

    public static final TextBuildFacade facade;

    public static SplitChain buildChains;

    static {
        initConfigs();
        SymbolSplit symbolSplit = new SymbolSplit("", TEXT_PART_FACTORY, Symbol.class);
        WordSplitChain wordSplitChain = new WordSplitChain(symbolSplit, "", TEXT_PART_FACTORY, Word.class);
        SentenceSplitChain sentenceSplitChain = new SentenceSplitChain(wordSplitChain, " ", TEXT_PART_FACTORY, Sentence.class);
        buildChains = new TextSplitChain(sentenceSplitChain, " ", TEXT_PART_FACTORY, CompositeTextPart.class);

        CONTEXT_PARAMS.put(TEXT_SPLIT_STRATEGY_KEY, buildChains);
        CONTEXT_PARAMS.put(SENTENCE_SPLIT_STRATEGY_KEY, sentenceSplitChain);
        CONTEXT_PARAMS.put(WORD_SPLIT_STRATEGY_KEY, wordSplitChain);
        facade = new TextBuildFacade(buildChains);

    }

    public static Object getParam(String key) {
        return CONTEXT_PARAMS.get(key);
    }

    public static void initConfigs() {
        try (FileInputStream fis = new FileInputStream("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project2.1\\resources\\config.properties")) {
            CONTEXT_PARAMS.load(fis);
            try {
                TEXT_PART_FACTORY = (TextPartFactory) Class.forName((String) CONTEXT_PARAMS.getProperty("textPartFactory")).newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
