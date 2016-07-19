package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parser.unmarshallingResultBuilder.XmlUnmarshallingResultBuilder;

import javax.xml.stream.XMLStreamConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxParser<E> extends DefaultHandler {

    private XmlUnmarshallingResultBuilder<E> resultBuilder;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String chars = new String(ch, start, length);
        if (chars.trim().isEmpty()) {
            return;
        }
        resultBuilder.buildPart(XMLStreamConstants.CHARACTERS, chars, Collections.EMPTY_MAP);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        resultBuilder.buildPart(XMLStreamConstants.START_ELEMENT, localName, prepareAttributes(attributes));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        resultBuilder.buildPart(XMLStreamConstants.END_ELEMENT, localName, Collections.EMPTY_MAP);
    }

    private Map<String, String> prepareAttributes(Attributes attributes) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < attributes.getLength(); i++) {
            result.put(attributes.getLocalName(i), attributes.getValue(i));
        }
        return result;
    }

    public void setResultBuilder(XmlUnmarshallingResultBuilder<E> resultBuilder) {
        this.resultBuilder = resultBuilder;
    }
}