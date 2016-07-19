package parser;

import entity.TagName;
import parser.parsingStrategy.AbstractTagParser;
import parser.parsingStrategy.FunctionalContext;

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
 * Class represents basis of streaming parsing technologies.
 * <p>
 * <p>
 * It works like finite state machine, based on sequence of {@link FunctionalContext},
 * that has tagName - functional strategy of mapping xml tags to Java objects. This
 * strategies are defined in {@link AbstractTagParser} type. Also this machine has
 * internal mapping between events, invoked by parser, and method reference, that
 * will be invoked because of this event.
 * </p>
 * <p>
 * As xml tags can have including tags and this tags are another complex types,
 * it's possible that current context can't parse new tag, so that it also can't
 * finish building the target object. If there's no context, that can parse this
 * tag - it will be omitted. Otherwise, this context puts to specific stack {@link #functionalContextStack},
 * and replaces from {@link #currentContext} by context, that can build element,
 * that represented by current tag hierarchy. While new element's building process is finished (event with
 * code {@link XMLStreamConstants#END_ELEMENT} was invoked), current context replaces by last context in
 * stack and machine will work in context, that was earlier replaced by actual one.
 * </p>
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see AbstractTagParser
 * @see FunctionalContext
 */
public abstract class FiniteStateAutomatonMarshaller<E> extends AbstractMarshaller<E> {

    /**
     * stack of functional parsing contexts.
     */
    private Stack<FunctionalContext> functionalContextStack = new Stack<>();

    /**
     * actualContext - parser is parsing elements using this parsing context.
     */
    private FunctionalContext currentContext;

    /**
     * mapping between tagName and parsing strategy.
     */
    private Map<TagName, FunctionalContext> tagContexts = new HashMap<>();

    /**
     * last parsed tag. Uses when there is no strategy for parsing specific tag,
     * but {@link XMLStreamConstants#CHARACTERS} event was invoked. Possibly
     * the insert data strategy is declared for this element in functional context.
     * This situation could possibly occur when there is no need to have specific
     * strategy for parsing start tag (it hasn't any attributes or specific object initializing
     * is not need).
     */
    private TagName lastTag; //TODO: должен быть стек вызванных тегов, иначе данные будет не туда вставлять!!!!

    /**
     * parsing result/
     */
    private E result;

    /**
     * internal transition functions. Used to map specific parsing method to event, that was occured
     * during parsing.
     */
    private Map<Integer, BiConsumer<String, Map<String, String>>> parsingFunctions = new HashMap<>();

    /**
     * creates stream marshaller, configuring it with parsing strategies
     * and xml schema location.
     *
     * @param xmlSchemaReader xml schema location
     * @param tagParserList   parsing strategies
     */
    public FiniteStateAutomatonMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList) {
        super(xmlSchemaReader);
        fillTagContexts(tagParserList);
        initXmlEventsToFunctionsMapping();
    }

    /**
     * creates stream marshaller, configuring it with parsing strategies
     * and xml schema.
     *
     * @param schema        xml schema
     * @param tagParserList parsing strategies
     */
    public FiniteStateAutomatonMarshaller(Schema schema, List<AbstractTagParser> tagParserList) {
        super(schema);
        fillTagContexts(tagParserList);
        functionalContextStack = new Stack<>();
        initXmlEventsToFunctionsMapping();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * configuring factory, calling {@link #configureFactory()}, then validating schema,
     * calling {@link #validate(Reader)}, and then calls {@link #unmarshallingHook(Reader)}
     * </p>
     *
     * @param xmlStream i/o of specific xml
     * @return Object representation of xml file with schema {@link #schema}
     */
    @Override
    public E unmarshalling(Reader xmlStream) {
        try {
            configureFactory();
            validate(xmlStream);
            return unmarshallingHook(xmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * internal transitions mapping configure method. Configure mapping
     * between {@link XMLStreamConstants} and parsing functions.
     */
    protected void initXmlEventsToFunctionsMapping() {
        parsingFunctions.put(XMLStreamConstants.START_ELEMENT, this::startElementEvent);
        parsingFunctions.put(XMLStreamConstants.CHARACTERS, (data, stringStringMap) -> charactersEvent(data));
        parsingFunctions.put(XMLStreamConstants.END_ELEMENT, (tagName, stringStringMap) -> endElementEvent(tagName));
    }

    /**
     * parsing technology specific chosen strategy. Must be implemented, because it's main parsing
     * algorithm.
     *
     * @param xmlStream xml document stream
     * @return object representation of this i/o stream
     * @throws Exception if any kind exception occurs during parsing
     */
    protected abstract E unmarshallingHook(Reader xmlStream) throws Exception;

    /**
     * configures parsing factory (can be omitted).
     */
    protected abstract void configureFactory();

    /**
     * validate xml document from specific steam using {@link #schema}
     *
     * @param xmlStream xml document stream
     */
    protected abstract void validate(Reader xmlStream);

    private void fillTagContexts(List<AbstractTagParser> tagParserList) {
        tagParserList.forEach(abstractTagParser -> tagContexts.put(abstractTagParser.getTagName(),
                abstractTagParser.getFunctionalContext()));
    }


    /**
     * accept parsing function from {@link #parsingFunctions} according to target xmlEventType. If there's no
     * function that is mapped to xml event
     *
     * @param stringParam  can be tagName or tag text content
     * @param attributes   attributes map, if {@link XMLStreamConstants#START_ELEMENT} was invoked, or empty map otherwise
     * @param xmlEventType {@link XMLStreamConstants}
     */
    void acceptParsingFunction(Integer xmlEventType, String stringParam, Map<String, String> attributes) {
        if (parsingFunctions.containsKey(xmlEventType)) {
            parsingFunctions.get(xmlEventType).accept(stringParam, attributes);
        }
    }

    /**
     * invokes, when {@link  XMLStreamConstants#START_ELEMENT} event is occur.
     * Tries to parse current tag using current parsing context. If it can't,
     * tries to find the context from {@link #tagContexts}, that has parsing
     * strategy for the tag. If there is no, omits current tag (just opening tag,
     * it will try to parse nested tags and other data)
     *
     * @param tagName    xml tag name
     * @param attributes tag attributes
     */
    private void startElementEvent(String tagName, Map<String, String> attributes) {
        lastTag = TagName.fromValue(tagName);
        System.out.println("startTag " + tagName);
        if (acceptParseFunctionFromCurrentContext(attributes)) {
            return;
        }

        //no tag parser in curentContext, try to find in other contexts
        FunctionalContext newTagContext = tagContexts.get(lastTag);
        if (newTagContext != null) {
            functionalContextStack.push(currentContext);
            currentContext = newTagContext;
            acceptParseFunctionFromCurrentContext(attributes);
        }
    }

    /**
     * tries to find function from current context. If it finds, accepts this function
     * with attributes as parameter.
     *
     * @param attributes tag attributes
     * @return true, if function was find from current context and was accepted. Otherwise, false.
     */
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

    /**
     * adds text context to last tag in tag stack.
     *
     * @param data parsed data
     */
    @SuppressWarnings("unchecked")
    private void charactersEvent(String data) {
        currentContext.getInsertFunction(lastTag).accept(data);
    }

    /**
     * invokes when {@link XMLStreamConstants#END_ELEMENT} event was invoked. Returns parsed object
     * by current context, changes context for previous, polling it from {@link #functionalContextStack}.
     * if there's no contexts in stack - root element closing tag was passed, initialises {@link #result}
     * field.
     *
     * @param tagName closing tag name
     */
    @SuppressWarnings("unchecked")
    private void endElementEvent(String tagName) {
        System.out.println("endTag " + tagName);
        Supplier resultFunction = currentContext.getResultFunction(TagName.fromValue(tagName));
        if (resultFunction != null) {
            currentContext = functionalContextStack.pop(); //set old context
            if (currentContext == null) {//root element end tag was read.
                result = (E) resultFunction.get();
            } else {
                currentContext.getInsertFunction(TagName.fromValue(tagName)).accept(resultFunction.get());
            }
        }
    }

    /**
     * return {@link #result} value
     *
     * @return {@link #result} value
     */
    protected E getResult() {
        return result;
    }
}