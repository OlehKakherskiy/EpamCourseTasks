package parser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import parser.unmarshallingResultBuilder.UnmarshallingResultBuilder;
import parser.unmarshallingResultBuilder.XmlUnmarshallingResultBuilder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.Reader;

;

/**
 * Sax algorithm for finite state automaton.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxMarshaller<E> extends XmlMarshaller<E> {

    /**
     * parser factory for configuring and creating parsers
     */
    private SAXParserFactory factory;

    /**
     * parser main parsing strategy
     */
    private SaxParser parser;

    public SaxMarshaller(Reader xmlSchemaReader, XmlUnmarshallingResultBuilder<E> resultBuilder, SaxParser parser) {
        super(xmlSchemaReader, resultBuilder);
        this.parser = parser;
        this.parser.setResultBuilder(resultBuilder);
    }

    public SaxMarshaller(Schema schema, UnmarshallingResultBuilder<E> resultBuilder) {
        super(schema, resultBuilder);
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
            return resultBuilder.getResult();
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
}