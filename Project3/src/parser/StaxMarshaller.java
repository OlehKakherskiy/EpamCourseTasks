package parser;

import entity.Medicines;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.OutputStream;
import java.io.Reader;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class StaxMarshaller extends AbstractMarshaller {


    @Override
    public void marshalling(Object element, OutputStream out) throws Exception {

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
