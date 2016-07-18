package parser;

import entity.Medicines;
import org.xml.sax.SAXException;
import parser.streamMarshaller.AbstractTagParser;

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
import java.io.*;
import java.util.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller extends StreamMarshaller {

    protected StringBuilder xmlStreamStorage = new StringBuilder();

    private XMLInputFactory xmlInputFactory;

    public StaxMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList) {
        super(xmlSchemaReader, tagParserList);
    }

    public StaxMarshaller(Schema schema, List<AbstractTagParser> tagParserList) {
        super(schema, tagParserList);
    }

    @Override
    protected Medicines unmarshallingHook(Reader xmlStream) {
        try {
            return parse(createReader(new StringReader(xmlStreamStorage.toString())));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void copyXmlStream(Reader xmlStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(xmlStream);
        bufferedReader.lines().forEach(xmlStreamStorage::append);
        bufferedReader.close();
    }

    @Override
    protected void configureFactory() {
        xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
    }

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

    protected Source getSource(Reader sourceReader) throws XMLStreamException {
        return new StAXSource(createReader(sourceReader));
    }

    private XMLEventReader createReader(Reader sourceReader) throws XMLStreamException {
        return xmlInputFactory.createXMLEventReader(sourceReader);
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