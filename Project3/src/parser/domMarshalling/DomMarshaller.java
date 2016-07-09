package parser.domMarshalling;


import org.w3c.dom.Document;
import parser.AbstractMarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DomMarshaller extends AbstractMarshaller {

    private DomParser parser;

    private DomSaver saver;

    public DomMarshaller(DomParser parser, DomSaver saver) {
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    public void marshalling(Medicines medicines, String fileName) throws Exception {
        nullAndEmptinessCheck(fileName);
        File f = getFile(fileName);
        Document document = saver.save(medicines);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformerFactory.newTransformer().transform(new DOMSource(document), new StreamResult(new FileWriter(f)));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new Exception("marshalling exception", e);
        }
    }

    @Override
    protected Source getSource(String xmlFilePath) throws Exception {
        File f = getFile(xmlFilePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = factory.newDocumentBuilder().parse(f);

        return new DOMSource(document);
    }

    @Override
    protected Medicines unmarshalling(Source source) {
        return parser.parse((Document) ((DOMSource) source).getNode());
    }

}