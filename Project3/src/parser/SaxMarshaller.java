package parser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import parser.parsingStrategy.AbstractTagParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

;

/**
 * Sax algorithm for finite state automaton.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxMarshaller<E> extends FiniteStateAutomatonMarshaller<E> {

    /**
     * parser factory for configuring and creating parsers
     */
    private SAXParserFactory factory;

    /**
     * parser main parsing strategy
     */
    private SaxParser parser;

    public SaxMarshaller(Reader xmlSchemaReader, List<AbstractTagParser> tagParserList, SaxParser parser) {
        super(xmlSchemaReader, tagParserList);
        this.parser = parser;
    }

    public SaxMarshaller(Schema schema, List<AbstractTagParser> tagParserList, SaxParser parser) {
        super(schema, tagParserList);
        this.parser = parser;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * creates new parser using factory and do parsing, using custom parser {@link SaxParser}
     * </p>
     *
     * @param xmlStream xml document stream
     * @return object representation of xml stream
     */
    @Override
    protected E unmarshallingHook(Reader xmlStream) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFactory() {
        factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(schema); //validating parameters
        factory.setValidating(true);
    }

    /**
     * {@inheritDoc}
     * <p>
     * do nothing. Factory is configured for create parsers with validation switching on
     * </p>
     *
     * @param xmlStream xml document stream
     */
    @Override
    protected void validate(Reader xmlStream) {
    }

    @Override
    public void marshalling(E element, Writer out) throws Exception {

    }
}