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

    private FiniteStateAutomatonMarshaller marshaller;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String chars = new String(ch, start, length);
        if (chars.trim().isEmpty()) {
            return;
        }
        marshaller.acceptParsingFunction(XMLStreamConstants.CHARACTERS, chars, Collections.EMPTY_MAP);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        marshaller.acceptParsingFunction(XMLStreamConstants.START_ELEMENT, localName, prepareAttributes(attributes));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        marshaller.acceptParsingFunction(XMLStreamConstants.END_ELEMENT, localName, Collections.EMPTY_MAP);
    }

    private Map<String, String> prepareAttributes(Attributes attributes) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < attributes.getLength(); i++) {
            result.put(attributes.getLocalName(i), attributes.getValue(i));
        }
        return result;
    }

    public void setMarshaller(FiniteStateAutomatonMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
