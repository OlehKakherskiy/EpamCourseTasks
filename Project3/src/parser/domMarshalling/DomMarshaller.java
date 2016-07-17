package parser.domMarshalling;


import entity.Medicines;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import parser.AbstractMarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import java.io.OutputStream;
import java.io.Reader;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DomMarshaller extends AbstractMarshaller<Medicines> {

    private DomParser parser;

    private DomSaver saver;

    public DomMarshaller(DomParser parser, DomSaver saver) {
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    public void marshalling(Medicines medicines, OutputStream outputStream) throws Exception {
        Document document = saver.save(medicines);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformerFactory.newTransformer().transform(new DOMSource(document), new StreamResult(outputStream));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new Exception("marshalling exception", e);
        }
    }

    @Override
    protected Source getSource(Reader xmlStream, Schema schema) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setNamespaceAware(true);
        factory.setSchema(schema);
//        factory.setValidating(true);
//        factory.setFeature("http://apache.org/xml/features/validation/schema", true);
        Document document = factory.newDocumentBuilder().parse(new InputSource(xmlStream)); //new File("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project3\\test\\resources\\testFiles\\file.xml")
        return new DOMSource(document);
    }

    @Override
    protected Medicines unmarshalling(Source source) {
        return parser.parse((Document) ((DOMSource) source).getNode());
    }

}