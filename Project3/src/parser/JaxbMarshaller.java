package parser;

import entity.Medicines;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class JaxbMarshaller extends AbstractMarshaller {


    public JaxbMarshaller(Reader xmlSchemaReader) {
        super(xmlSchemaReader);
    }

    public JaxbMarshaller(Schema schema) {
        super(schema);
    }

    @Override
    public void marshalling(Object element, Writer out) throws Exception {

    }

    @Override
    protected Source getSource(Reader xmlStream, Schema schema) throws Exception {
        return null;
    }

    @Override
    protected Medicines unmarshalling(Source source) {
        return null;
    }
}
