package parser;

import entity.Medicines;
import parser.streamMarshaller.AbstractTagParser;
import parser.streamMarshaller.StreamMarshaller;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller extends StreamMarshaller {

    public StaxMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList) {
        super(xmlSchemaReader, tagParserList);
    }

    public StaxMarshaller(Schema schema, List<AbstractTagParser> tagParserList) {
        super(schema, tagParserList);
    }

    @Override
    public Medicines unmarshalling(Reader xmlStream) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_COALESCING, true);
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
            XMLEventReader reader = factory.createXMLEventReader(xmlStream);
//            schema.newValidator().validate(new StAXSource(reader));

//            reader.close();
            Medicines result = parse(reader);
            return result;
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Medicines parse(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = null;
        while (reader.hasNext()) {
            event = reader.nextEvent();
            Map<String, String> attributes = null;
            String stringParam = null;
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement startElement = event.asStartElement();
                    prepareParamsAndAcceptFunction(startElement.getName().getLocalPart(), getAttributes(startElement),
                            XMLStreamConstants.START_ELEMENT);
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    stringParam = event.asCharacters().getData().trim();
                    if (!stringParam.isEmpty()) {
                        prepareParamsAndAcceptFunction(stringParam, attributes, XMLStreamConstants.CHARACTERS);
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    prepareParamsAndAcceptFunction(event.asEndElement().getName().getLocalPart(),
                            Collections.emptyMap(), XMLStreamConstants.END_ELEMENT);
                    break;
                }
            }
        }
        return getResult();
    }

    private void prepareParamsAndAcceptFunction(String stringParam, Map<String, String> attributes, int eventType) {
        acceptParsingFunction(eventType, stringParam, attributes);
    }

    private Map<String, String> getAttributes(StartElement startElement) {
        Map<String, String> result = new HashMap<>();
        Iterator attrIterator = startElement.getAttributes();
        while (attrIterator.hasNext()) {
            Attribute attribute = (Attribute) attrIterator.next();
            result.put(attribute.getName().getLocalPart(), attribute.getValue());
        }
        return result;
    }

    @Override
    public void marshalling(Medicines element, Writer out) throws Exception {
        throw new UnsupportedOperationException("Can't marshall with StAX parser");
    }
}