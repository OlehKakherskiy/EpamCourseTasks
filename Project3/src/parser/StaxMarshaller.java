package parser;

import entity.Medicines;

import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller extends AbstractMarshaller<Medicines> {


    public StaxMarshaller(Reader xmlSchemaReader) {
        super(xmlSchemaReader);
    }

    public StaxMarshaller(Schema schema) {
        super(schema);
    }

    @Override
    public Medicines unmarshalling(Reader xmlStream) {
        return null;
    }

    @Override
    public void marshalling(Medicines element, Writer out) throws Exception {

    }
}
