package parser;

import org.xml.sax.SAXException;
import parser.unmarshallingResultBuilder.UnmarshallingResultBuilder;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class XmlMarshaller<E> extends AbstractMarshaller<E> {

    /**
     * Xml schema of specific xml, that will be load/save
     */
    protected Schema schema;

    /**
     * Constructs marshaller with i/o stream of schema
     *
     * @param xmlSchemaReader i/o stream from which schema will be created
     */
    public XmlMarshaller(Reader xmlSchemaReader, UnmarshallingResultBuilder<E> resultBuilder) {
        super(resultBuilder);
        nullCheck(xmlSchemaReader);
        try {
            schema = getDocumentSchema(xmlSchemaReader);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs marshaller, that will be used for xml mapping
     * with specific schema
     *
     * @param schema schema of mapped xml
     */
    public XmlMarshaller(Schema schema, UnmarshallingResultBuilder<E> resultBuilder) {
        super(resultBuilder);
        nullCheck(schema);
        this.resultBuilder = resultBuilder;
        this.schema = schema;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * Configuring factory, calling {@link #configureFactory()}, then validating schema,
     * calling {@link #validate(Reader)}, and then calls {@link #unmarshallingHook(Reader)}
     * </p>
     *
     * @param xmlStream i/o of specific xml
     * @return Object representation of xml file with schema {@link #schema}
     */
    public E unmarshalling(Reader xmlStream) {
        try {
            configureFactory();
            validate(xmlStream);
            return unmarshallingHook(xmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * default implementation - throws {@link UnsupportedOperationException}
     * </p>
     *
     * @param element
     * @param out
     * @throws Exception
     */
    public void marshalling(E element, Writer out) throws Exception {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * parsing technology specific chosen strategy. Must be implemented, because it's main parsing
     * algorithm.
     *
     * @param xmlStream xml document stream
     * @return object representation of this i/o stream
     * @throws Exception if any kind exception occurs during parsing
     */
    protected abstract E unmarshallingHook(Reader xmlStream) throws Exception;

    /**
     * configures parsing factory (can be omitted).
     */
    protected abstract void configureFactory();

    /**
     * validate xml document from specific steam using {@link #schema}.
     * Default implementation is no implementation. Can be override in
     * subclasses for xm validation or just paste implementation to {@link #unmarshallingHook(Reader)}
     *
     * @param xmlStream xml document stream
     */
    protected void validate(Reader xmlStream) {
    }


    /**
     * creates xml schema from specific i/o stream
     *
     * @param schemaStream xml schema input stream
     * @return xml schema of future xml
     * @throws SAXException if exception occurs during parsing
     */
    private Schema getDocumentSchema(Reader schemaStream) throws SAXException {
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource(schemaStream));
    }
}
