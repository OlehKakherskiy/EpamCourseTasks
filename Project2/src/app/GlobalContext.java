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

    static {
        initConfigs();
    }

    public static Object getParam(String key) {
        return CONTEXT_PARAMS.get(key);
    }


    public static void initConfigs() {
        try (FileInputStream fis = new FileInputStream("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project1\\resources\\config.properties")) {
            CONTEXT_PARAMS.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
