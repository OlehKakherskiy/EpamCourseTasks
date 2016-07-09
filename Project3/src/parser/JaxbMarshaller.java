package parser;

import javax.xml.transform.Source;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class JaxbMarshaller extends AbstractMarshaller {

    @Override
    public void marshalling(Medicines medicines) {

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
