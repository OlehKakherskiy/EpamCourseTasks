package parser;

import entity.Medicines;
import entity.TagName;
import parser.streamMarshaller.AbstractTagParser;
import parser.streamMarshaller.FunctionalContext;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class StreamMarshaller extends AbstractMarshaller<Medicines> {


    private Stack<FunctionalContext> functionalContextStack;

    private FunctionalContext currentContext;

    private Map<TagName, FunctionalContext> tagContexts = new HashMap<>();

    private TagName lastTag;

    private Medicines result;

    private Map<Integer, BiConsumer<String, Map<String, String>>> parsingFunctions = new HashMap<>();

    {
        parsingFunctions.put(XMLStreamConstants.START_ELEMENT, this::startElementEvent);
        parsingFunctions.put(XMLStreamConstants.CHARACTERS, (data, stringStringMap) -> charactersEvent(data));
        parsingFunctions.put(XMLStreamConstants.END_ELEMENT, (tagName, stringStringMap) -> endElementEvent(tagName));
        parsingFunctions.put(XMLStreamConstants.END_DOCUMENT, (s, stringStringMap) -> endDocumentEvent());
    }

    public StreamMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList) {
        super(xmlSchemaReader);
        functionalContextStack = new Stack<>();
        fillTagContexts(tagParserList);
    }

    public StreamMarshaller(Schema schema, List<AbstractTagParser> tagParserList) {
        super(schema);
        fillTagContexts(tagParserList);
        functionalContextStack = new Stack<>();
    }


    @Override
    public Medicines unmarshalling(Reader xmlStream) {
        try {
            configureFactory();
            validate(xmlStream);
            return unmarshallingHook(xmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Medicines unmarshallingHook(Reader xmlStream) throws Exception;

    protected abstract void configureFactory();

    protected abstract void validate(Reader xmlStream);

    private void fillTagContexts(List<AbstractTagParser> tagParserList) {
        tagParserList.forEach(abstractTagParser -> tagContexts.put(abstractTagParser.getTagName(),
                abstractTagParser.getFunctionalContext()));
    }

    private void startElementEvent(String tagName, Map<String, String> attributes) {
        lastTag = TagName.fromValue(tagName);
        System.out.println("startTag " + tagName);
        if (acceptParseFunctionFromCurrentContext(attributes)) {
            return;
        }

        //no tag parser in curentContext, try to find in other contexts
        FunctionalContext newTagContext = tagContexts.get(lastTag);
        if (newTagContext != null) {
            functionalContextStack.push(currentContext); //TODO: тут null пихаем сначала - нужно ли?
            currentContext = newTagContext;
            acceptParseFunctionFromCurrentContext(attributes);
        }
    }

    private boolean acceptParseFunctionFromCurrentContext(Map<String, String> attributes) {
        if (currentContext == null)
            return false;
        Consumer<Map<String, String>> parser = currentContext.getInitFunction(lastTag);
        if (parser != null) {
            parser.accept(attributes);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void charactersEvent(String data) {
        currentContext.getInsertFunction(lastTag).accept(data);
    }

    @SuppressWarnings("unchecked")
    private void endElementEvent(String tagName) {
        System.out.println("endTag " + tagName);
        Supplier resultFunction = currentContext.getResultFunction(TagName.fromValue(tagName));
        if (resultFunction != null) {
            currentContext = functionalContextStack.pop(); //set old context
            if (currentContext == null) {//root element end tag was read.
                result = (Medicines) resultFunction.get();
            } else {
                currentContext.getInsertFunction(TagName.fromValue(tagName)).accept(resultFunction.get());
            }
        }
    }

    private void endDocumentEvent() {
        System.out.println("endDoc");
    }

    protected Medicines getResult() {
        return result;
    }

    protected void prepareParamsAndAcceptFunction(String stringParam, Map<String, String> attributes, int eventType) {
        acceptParsingFunction(eventType, stringParam, attributes);
    }

    private void acceptParsingFunction(Integer xmlEvent, String stringParam, Map<String, String> attributes) {
        if (parsingFunctions.containsKey(xmlEvent)) {
            parsingFunctions.get(xmlEvent).accept(stringParam, attributes);
        }
    }


}