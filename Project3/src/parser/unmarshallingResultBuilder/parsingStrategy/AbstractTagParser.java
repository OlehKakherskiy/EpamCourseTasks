package parser.unmarshallingResultBuilder.parsingStrategy;

import entity.TagName;

import java.util.Map;

/**
 * Root of specific tag parsing strategies. Each element has encapsulated
 * strategies for parsing a piece of specific type represented information,
 * starting from {@link #tagName} and all his child tags. If child tag is
 * complex, may be used another {@link AbstractTagParser}, and a result
 * of his processing may be inserted to target object of current parser.
 * <p>
 * Each tag parser must be configured by method implementation
 * {@link #initFunctionalContext()}
 * </p>
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractTagParser<E> {

    protected FunctionalContext functionalContext;

    /**
     * {@link TagName}, object representation of which is parsed by this tag parser
     */
    private TagName tagName;

    /**
     * prepared element, will be built step-by-step
     */
    protected E element;

    public AbstractTagParser(TagName tagName) {
        this.functionalContext = new FunctionalContext();
        functionalContext.addInitFunction(tagName, this::startElementParsing);
        functionalContext.addGetResultFunction(tagName, this::getParsingResult);
        initFunctionalContext();
        this.tagName = tagName;
    }

    /**
     * returns parsed object representation of this {@link TagName}
     *
     * @return {@link #element} value
     */
    public E getParsingResult() {
        return element;
    }

    /**
     * configuring information about parsing subelements of this tag
     */
    protected abstract void initFunctionalContext();

    /**
     * method inits target object with information, represented in attributes parameter.
     *
     * @param attributes init attributes
     */
    protected abstract void startElementParsing(Map<String, String> attributes);

    /**
     * returns {@link #functionalContext} object
     *
     * @return field {@link #functionalContext} value
     */
    public FunctionalContext getFunctionalContext() {
        return functionalContext;
    }

    /**
     * returns {@link #tagName} field value
     *
     * @return {@link #tagName} field value
     */
    public TagName getTagName() {
        return tagName;
    }

    String getAttributeValue(Map<String, String> attributes, TagName attName) {
        return attributes.get(attName.getString());
    }
}