package parser;

import entity.Medicines;

import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class JaxbMarshaller extends AbstractMarshaller<Medicines> {


    public JaxbMarshaller(Reader xmlSchemaReader) {
        super(xmlSchemaReader);
    }

    public JaxbMarshaller(Schema schema) {
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
