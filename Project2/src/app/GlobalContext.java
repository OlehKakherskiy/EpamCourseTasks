package app;

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

    public static final String TEXT_PART_FACTORY_KEY = "textPartFactory";

    public static final String SENTENCE_PART_SPLIT_REGEXP_KEY = "sentencePartSplitRegexp";

    public static final String TEXT_SPLIT_STRATEGY_KEY = "textSplitStrategy";

    public static final String SENTENCE_SPLIT_STRATEGY_KEY = "sentenceSplitStrategy";

    public static final String WORD_SPLIT_STRATEGY_KEY = "wordSplitStrategy";

    public static final String UNSUPPORTED_OPERATION_TEXT_KEY = "unsupportedOperationException";

    public static final String TEXT_SPLIT_PATTERN_KEY = "splitSentenceTokenizer";

    static {
        initConfigs();
    }

    public static Object getParam(String key) {
        return CONTEXT_PARAMS.get(key);
    }


    public static void initConfigs() {
        try (FileInputStream fis = new FileInputStream("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project2\\resources\\config.properties")) {
            CONTEXT_PARAMS.load(fis);
            CONTEXT_PARAMS.put(TEXT_SPLIT_STRATEGY_KEY,Class.forName((String) CONTEXT_PARAMS.get(TEXT_SPLIT_STRATEGY_KEY)));
            CONTEXT_PARAMS.put(SENTENCE_SPLIT_STRATEGY_KEY,Class.forName((String) CONTEXT_PARAMS.get(SENTENCE_SPLIT_STRATEGY_KEY)));
            CONTEXT_PARAMS.put(WORD_SPLIT_STRATEGY_KEY,Class.forName((String) CONTEXT_PARAMS.get(WORD_SPLIT_STRATEGY_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
