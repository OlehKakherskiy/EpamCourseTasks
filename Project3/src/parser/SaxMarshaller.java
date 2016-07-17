package parser;

import entity.Medicines;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxMarshaller extends AbstractMarshaller {


    public SaxMarshaller(Reader xmlSchemaReader) {
        super(xmlSchemaReader);
    }

    public SaxMarshaller(Schema schema) {
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
