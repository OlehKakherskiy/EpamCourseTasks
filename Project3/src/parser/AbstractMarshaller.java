package parser;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractMarshaller<E> {

    protected Reader xmlSchemaReader;

    protected Schema schema;

    public AbstractMarshaller(Reader xmlSchemaReader) {
        this.xmlSchemaReader = xmlSchemaReader;
        nullCheck(xmlSchemaReader);
        try {
            schema = getDocumentSchema(xmlSchemaReader);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public AbstractMarshaller(Schema schema) {
        nullCheck(schema);
        this.schema = schema;
    }

    public abstract E unmarshalling(Reader xmlStream);

    public abstract void marshalling(E element, Writer out) throws Exception;

    private Schema getDocumentSchema(Reader schemaStream) throws SAXException {
        return SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new StreamSource(schemaStream));
    }

    protected void nullCheck(Object stream) {
        if (stream == null) {
            throw new IllegalArgumentException("stream is null");
        }
    }
}