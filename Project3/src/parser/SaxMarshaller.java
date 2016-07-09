package parser;

import javax.xml.transform.Source;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SaxMarshaller extends AbstractMarshaller {

    @Override
    public void marshalling(Medicines medicines, String fileName) throws Exception {
        throw new UnsupportedOperationException("can't build xml from object using this parser");
    }

    @Override
    protected Source getSource(String xmlFilePath) throws Exception {
        return null;
    }

    @Override
    protected Medicines unmarshalling(Source source) {
        return null;
    }

}
