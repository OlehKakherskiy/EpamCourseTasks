package parser.domMarshalling;


import entity.Medicines;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import parser.AbstractMarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DomMarshaller extends AbstractMarshaller<Medicines> {

    private DomParser parser;

    private DomSaver saver;

    public DomMarshaller(Reader xmlSchemaReader, DomParser parser, DomSaver saver) {
        super(xmlSchemaReader);
        this.parser = parser;
        this.saver = saver;
    }

    public DomMarshaller(Schema schema, DomParser parser, DomSaver saver) {
        super(schema);
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    public Medicines unmarshalling(Reader xmlStream) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setNamespaceAware(true);
        factory.setSchema(schema);
        try {
            return parser.parse(factory.newDocumentBuilder().parse(new InputSource(xmlStream)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void marshalling(Medicines medicines, Writer out) throws Exception {
        Document document = saver.save(medicines, schema);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //pretty formatting
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        try {
            transformer.transform(new DOMSource(document), new StreamResult(out));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new Exception("marshalling exception", e);
        }
    }
}