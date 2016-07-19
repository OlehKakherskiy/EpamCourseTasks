package parser.unmarshallingResultBuilder.parsingStrategy;

import entity.TagName;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class represents building context for {@link AbstractTagParser} hierarchy.
 * It encapsulates functional mapping for creating object, inserting information
 * and returning result
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FunctionalContext {

    /**
     * mapping tagName to creating object function
     */
    private Map<TagName, Consumer<Map<String, String>>> initFunctions = new HashMap<>();

    /**
     * mapping tagName to insertion data function
     */
    private Map<TagName, Consumer> insertDataFunctions = new HashMap<>();

    /**
     * mapping tagName to finish building and returning result element function
     */
    private Map<TagName, Supplier> getResultFunctions = new HashMap<>();

    /**
     * adds init object function
     *
     * @param tagName {@link TagName} object representation of which will be created
     * @param creator function, that instantiates object representation and can insert some
     *                specific init data.
     */
    public void addInitFunction(TagName tagName, Consumer<Map<String, String>> creator) {
        initFunctions.put(tagName, creator);
    }

    /**
     * adds specific insert data function
     *
     * @param tagName {@link TagName} object representation of which will be inserted to result object
     * @param builder function that inserts object representation of tag to result object
     */
    public void addInsertDataFunction(TagName tagName, Consumer builder) {
        insertDataFunctions.put(tagName, builder);
    }

    /**
     * adds specific finish building and get result data function
     *
     * @param tagName  {@link TagName} object representation of which will be returned as a result
     * @param supplier object returning function
     */
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
