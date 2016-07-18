package parser.streamMarshaller;

import entity.TagName;

import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractTagParser<E> {

    protected FunctionalContext functionalContext;

    protected TagName tagName;

    protected E element;

    public AbstractTagParser(TagName tagName) {
        this.functionalContext = new FunctionalContext();
        functionalContext.addInitFunction(tagName, this::startElementParsing);
        functionalContext.addGetResultFunction(tagName, this::getParsingResult);
        this.tagName = tagName;
    }

    public FunctionalContext getFunctionalContext() {
        return functionalContext;
    }

    public TagName getTagName() {
        return tagName;
    }

    public E getParsingResult() {
        return element;
    }

    protected String getAttributeValue(Map<String, String> attributes, TagName attName) {
        return attributes.get(attName.getString());
    }

    protected abstract void startElementParsing(Map<String, String> attributes);
}