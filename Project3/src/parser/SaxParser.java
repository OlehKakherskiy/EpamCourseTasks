package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLStreamConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxParser extends DefaultHandler {

    private StreamMarshaller marshaller;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String chars = new String(ch, start, length);
        if (chars.trim().isEmpty()) {
            return;
        }
        marshaller.prepareParamsAndAcceptFunction(chars, Collections.emptyMap(), XMLStreamConstants.CHARACTERS);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        marshaller.prepareParamsAndAcceptFunction(localName, prepareAttributes(attributes), XMLStreamConstants.START_ELEMENT);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        marshaller.prepareParamsAndAcceptFunction(localName, Collections.emptyMap(), XMLStreamConstants.END_ELEMENT);
    }

    private Map<String, String> prepareAttributes(Attributes attributes) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < attributes.getLength(); i++) {
            result.put(attributes.getLocalName(i), attributes.getValue(i));
        }
        return result;
    }

    public void setMarshaller(StreamMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
