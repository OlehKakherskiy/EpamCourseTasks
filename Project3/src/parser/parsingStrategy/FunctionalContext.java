package parser.parsingStrategy;

import entity.TagName;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FunctionalContext {

    private Map<TagName, Consumer<Map<String, String>>> initFunctions = new HashMap<>();

    private Map<TagName, Consumer> insertDataFunctions = new HashMap<>();

    private Map<TagName, Supplier> getResultFunctions = new HashMap<>();

    public void addInitFunction(TagName tagName, Consumer<Map<String, String>> parser) {
        initFunctions.put(tagName, parser);
    }

    public void addInsertDataFunction(TagName tagName, Consumer builder) {
        insertDataFunctions.put(tagName, builder);
    }

    public void addGetResultFunction(TagName tagName, Supplier supplier) {
        getResultFunctions.put(tagName, supplier);
    }

    public Consumer<Map<String, String>> getInitFunction(TagName tagName) {
        return initFunctions.get(tagName);
    }

    public Consumer getInsertFunction(TagName tagName) {
        return insertDataFunctions.get(tagName);
    }

    public Supplier getResultFunction(TagName tagName) {
        return getResultFunctions.get(tagName);
    }
}
