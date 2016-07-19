package parser;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * Class is a root of marshalling hierarchy. It's used for marshalling/unmarshalling xml
 * documents from/to different i/o streams.
 *
 * @param <E> java type, mapped to specific xml stream
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractMarshaller<E> {

    /**
     * xml schema of specific xml, that will be load/save
     */
    protected Schema schema;

    /**
     * Constructs marshaller with i/o stream of schema
     *
     * @param xmlSchemaReader i/o stream from which schema will be created
     */
    public AbstractMarshaller(Reader xmlSchemaReader) {
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
    public AbstractMarshaller(Schema schema) {
        nullCheck(schema);
        this.schema = schema;
    }

    /**
     * parse xml from specific i/o to target object type
     *
     * @param xmlStream i/o of specific xml
     * @return object representation of xml
     */
    public abstract E unmarshalling(Reader xmlStream);

    /**
     * save specific object to target xml representation
     *
     * @param element object to be save to xml
     * @param out     xml output stream
     * @throws Exception if exception of any type will be caused
     */
    public abstract void marshalling(E element, Writer out) throws Exception;

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

    /**
     * null check, throws {@link IllegalArgumentException}
     *
     * @param stream any object
     */
    protected void nullCheck(Object stream) {
        if (stream == null) {
            throw new IllegalArgumentException("stream is null");
        }
    }
}