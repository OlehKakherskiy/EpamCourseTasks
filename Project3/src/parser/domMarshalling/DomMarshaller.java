package parser.domMarshalling;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import parser.XmlMarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DomMarshaller<E> extends XmlMarshaller<E> {

    private DomParser<E> parser;

    private DomSaver<E> saver;

    private DocumentBuilderFactory builderFactory;

    private TransformerFactory transformerFactory;

    public DomMarshaller(Reader xmlSchemaReader, DomParser<E> parser, DomSaver<E> saver) {
        super(xmlSchemaReader, null);
        this.parser = parser;
        this.saver = saver;
    }

    public DomMarshaller(Schema schema, DomParser<E> parser, DomSaver<E> saver) {
        super(schema, null);
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    protected E unmarshallingHook(Reader xmlStream) throws Exception {
        Document document = builderFactory.newDocumentBuilder().parse(new InputSource(xmlStream));
        schema.newValidator().validate(new DOMSource(document));
        return parser.parse(document);
    }

    @Override
    protected void configureFactory() {
        builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        builderFactory.setNamespaceAware(true);

        transformerFactory = TransformerFactory.newInstance();
    }

    @Override
    public void marshalling(E element, Writer out) throws Exception {
        Document document = saver.save(element, schema);
        Transformer transformer = configureTransformer(transformerFactory.newTransformer());
        try {
            transformer.transform(new DOMSource(document), new StreamResult(out));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new Exception("marshalling exception", e);
        }
    }

    private Transformer configureTransformer(Transformer transformer) {
        //pretty formatting
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }
}