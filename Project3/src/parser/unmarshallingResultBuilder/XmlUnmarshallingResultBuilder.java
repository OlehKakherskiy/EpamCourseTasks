package parser.unmarshallingResultBuilder;

import entity.TagName;
import parser.unmarshallingResultBuilder.parsingStrategy.AbstractTagParser;
import parser.unmarshallingResultBuilder.parsingStrategy.FunctionalContext;

import javax.xml.stream.XMLStreamConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
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
public class XmlUnmarshallingResultBuilder<E> implements UnmarshallingResultBuilder<E> {

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
    private Stack<TagName> tagStack = new Stack<>();

    /**
     * parsing result/
     */
    private E result;

    /**
     * internal transition functions. Used to map specific parsing method to event, that was occured
     * during parsing.
     */
    private Map<Integer, BiConsumer<String, Map<String, String>>> parsingFunctions = new HashMap<>();

    public XmlUnmarshallingResultBuilder(List<AbstractTagParser> tagParserList) {
        fillTagContexts(tagParserList);
        initXmlEventsToFunctionsMapping();
    }

    /**
     * {@inheritDoc}
     *
     * @return prepared object representation of xml stream
     */
    @Override
    public E getResult() {
        return result;
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
        tagStack.push(TagName.fromValue(tagName));
        System.out.println("startTag " + tagName);
        if (acceptParseFunctionFromCurrentContext(attributes)) {
            return;
        }

        //no tag parser in curentContext, try to find in other contexts
        FunctionalContext newTagContext = tagContexts.get(tagStack.peek());
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
        Consumer<Map<String, String>> parser = currentContext.getInitFunction(tagStack.peek());
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
        currentContext.getInsertFunction(tagStack.peek()).accept(data);
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
        tagStack.pop();
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
     * accept parsing function from {@link #parsingFunctions} according to target xmlEventType. If there's no
     * function that is mapped to xml event
     *
     * @param stringParam  can be tagName or tag text content
     * @param attributes   attributes map, if {@link XMLStreamConstants#START_ELEMENT} was invoked, or empty map otherwise
     * @param xmlEventType {@link XMLStreamConstants}
     */
    public void buildPart(Integer xmlEventType, String stringParam, Map<String, String> attributes) {
        if (parsingFunctions.containsKey(xmlEventType)) {
            parsingFunctions.get(xmlEventType).accept(stringParam, attributes);
        }
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
     * reformats list of {@link AbstractTagParser} to key/value representation,
     * where key - {@link AbstractTagParser#element}, value - {@link AbstractTagParser#functionalContext}
     *
     * @param tagParserList list all tags, that can be parsed
     */
    private void fillTagContexts(List<AbstractTagParser> tagParserList) {
        tagParserList.forEach(abstractTagParser -> tagContexts.put(abstractTagParser.getTagName(),
                abstractTagParser.getFunctionalContext()));
    }
}
