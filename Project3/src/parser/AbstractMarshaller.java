package parser;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.OutputStream;
import java.io.Reader;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractMarshaller<E> {

    public final E unmarshalling(Reader xmlStream, Reader xmlSchemaStream) throws Exception {
        nullAndEmptinessCheck(xmlStream);
        nullAndEmptinessCheck(xmlSchemaStream);
        try {
            Schema schema = getDocumentSchema(xmlSchemaStream);
            Source source = getSource(xmlStream, schema);
            schema.newValidator().validate(source);
            return unmarshalling(source);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("xml file with path: " + xmlStream.toString() + " " +
                    "isn't valid according to schema with path: " + xmlSchemaStream.toString());
        }
    }

    public abstract void marshalling(E element, OutputStream out) throws Exception;

    protected abstract Source getSource(Reader xmlStream, Schema schema) throws Exception;

    protected abstract E unmarshalling(Source source);

    private Schema getDocumentSchema(Reader schemaStream) throws SAXException {
        return SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new StreamSource(schemaStream));
    }

    protected void nullAndEmptinessCheck(Object stream) {
        if (stream == null) {
            throw new IllegalArgumentException("stream is null");
        }
    }
}