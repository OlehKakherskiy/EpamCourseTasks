package parser;

import entity.Medicines;
import org.xml.sax.SAXException;
import parser.streamMarshaller.FunctionalContext;
import parser.streamMarshaller.StreamMarshaller;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller extends StreamMarshaller {


    public StaxMarshaller(Reader xmlSchemaReader, FunctionalContext functionalContext) {
        super(xmlSchemaReader, functionalContext);
    }

    public StaxMarshaller(Schema schema, FunctionalContext functionalContext) {
        super(schema, functionalContext);
    }

    @Override
    public void startParsing(XMLEvent event) {

    }

    @Override
    public Medicines unmarshalling(Reader xmlStream) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(xmlStream);
            schema.newValidator().validate(new StAXSource(reader));
            return parse(reader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Medicines parse(XMLEventReader reader) throws XMLStreamException {
        //TODO:
        return null;
    }

    @Override
    public void marshalling(Medicines element, Writer out) throws Exception {
        throw new UnsupportedOperationException("Can't marshall with StAX parser");
    }
}
