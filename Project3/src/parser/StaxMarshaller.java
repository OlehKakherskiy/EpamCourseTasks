package parser;

import org.xml.sax.SAXException;
import parser.unmarshallingResultBuilder.UnmarshallingResultBuilder;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class represents STaX parsing technology.
 * Saves character data from stream to local storage and uses this storage twice:
 * while validates xml and while parses.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller<E> extends XmlMarshaller<E> {

    private StringBuilder xmlStreamStorage = new StringBuilder();

    private XMLInputFactory xmlInputFactory;

    public StaxMarshaller(Reader xmlSchemaReader, UnmarshallingResultBuilder<E> resultBuilder) {
        super(xmlSchemaReader, resultBuilder);
    }

    public StaxMarshaller(Schema schema, UnmarshallingResultBuilder<E> resultBuilder) {
        super(schema, resultBuilder);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * parses document from local storage {@link #xmlStreamStorage}
     * </p>
     *
     * @param xmlStream isn't used
     * @return object representation of input xml
     */
    @Override
    protected E unmarshallingHook(Reader xmlStream) {
        try {
            return parse(createReader(new StringReader(xmlStreamStorage.toString())));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFactory() {
        xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
    }

    /**
     * {@inheritDoc}
     * <p>
     * copies xml stream to local storage {@link #xmlStreamStorage}, creates new {@link javax.xml.validation.Validator}
     * from {@link #schema} and validates it.
     * </p>
     *
     * @param xmlStream xml document stream
     */
    @Override
    protected void validate(Reader xmlStream) {
        try {
            copyXmlStream(xmlStream);
            schema.newValidator().validate(getSource(new StringReader(xmlStreamStorage.toString())));
        } catch (IOException | SAXException | XMLStreamException e) {
            e.printStackTrace();
            throw new RuntimeException("xml validation failed while StAX parser started to parse");
        }
    }

    /**
     * copies xml stream to local storage {@link #xmlStreamStorage}.
     *
     * @param xmlStream
     * @throws IOException
     */
    private void copyXmlStream(Reader xmlStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(xmlStream);
        bufferedReader.lines().forEach(xmlStreamStorage::append);
        bufferedReader.close();
    }

    /**
     * creates {@link Source} from input stream for next using in validation and parsing
     * processes.
     *
     * @param sourceReader input stream
     * @return {@link Source} representation of xml stream
     * @throws XMLStreamException if problem with creating xml stream occurs
     */
    private Source getSource(Reader sourceReader) throws XMLStreamException {
        return new StAXSource(createReader(sourceReader));
    }

    /**
     * creates {@link XMLEventReader} from factory {@link #xmlInputFactory}, using specific
     * input stream
     *
     * @param sourceReader input stream, that contains target xml
     * @return xml event reader for StAX parsing processing.
     * @throws XMLStreamException if problem with creating xml stream occurs
     */
    private XMLEventReader createReader(Reader sourceReader) throws XMLStreamException {
        return xmlInputFactory.createXMLEventReader(sourceReader);
    }

    /**
     * represents main transition algorithm. Processes when {@link XMLStreamConstants#START_ELEMENT},
     * {@link XMLStreamConstants#END_ELEMENT}, {@link XMLStreamConstants#CHARACTERS} events occurs,
     * other events will be omitted.
     *
     * @param reader specific i/o stream, that contains
     * @return object representation of specific i/o stream
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private E parse(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = null;
        while (reader.hasNext()) {
            event = reader.nextEvent();
            Map<String, String> attributes = null;
            String stringParam = null;
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement startElement = event.asStartElement();
                    resultBuilder.buildPart(XMLStreamConstants.START_ELEMENT,
                            startElement.getName().getLocalPart(), getAttributes(startElement));
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    stringParam = event.asCharacters().getData().trim();
                    if (!stringParam.isEmpty()) {
                        resultBuilder.buildPart(XMLStreamConstants.CHARACTERS, stringParam, attributes);
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    resultBuilder.buildPart(XMLStreamConstants.END_ELEMENT, event.asEndElement().getName().getLocalPart(),
                            Collections.emptyMap());
                    break;
                }
            }
        }
        return resultBuilder.getResult();
    }

    /**
     * creates attributes from {@link StartElement#getAttributes()} iterator in key/value
     * format. If there's no attributes - empty map will be returned.
     *
     * @param startElement start tag element {@link StartElement}
     * @return key/value format of tag attributes
     */
    private Map<String, String> getAttributes(StartElement startElement) {
        Map<String, String> result = new HashMap<>();
        Iterator attrIterator = startElement.getAttributes();
        while (attrIterator.hasNext()) {
            Attribute attribute = (Attribute) attrIterator.next();
            result.put(attribute.getName().getLocalPart(), attribute.getValue());
        }
        return result;
    }
}