package parser;

import entity.Medicines;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import parser.streamMarshaller.AbstractTagParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxMarshaller extends StreamMarshaller {

    private SAXParserFactory factory;

    private SaxParser parser;

    public SaxMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList, SaxParser parser) {
        super(xmlSchemaReader, tagParserList);
        this.parser = parser;
    }

    public SaxMarshaller(Schema schema, List<AbstractTagParser> tagParserList, SaxParser parser) {
        super(schema, tagParserList);
        this.parser = parser;
    }

    @Override
    protected Medicines unmarshallingHook(Reader xmlStream) {
        try {
            factory.newSAXParser().parse(new InputSource(xmlStream), parser);
            return getResult();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void configureFactory() {
        factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(schema); //validating parameters
        factory.setValidating(true);
    }

    @Override
    protected void validate(Reader xmlStream) {
        return;
    }

    @Override
    public void marshalling(Medicines element, Writer out) throws Exception {

    }
}