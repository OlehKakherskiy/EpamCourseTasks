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
 * Class represents DOM technology for servicing marshalling/unmarshalling
 * operations between XML and Java data format representations.
 * <p>
 * Uses specific type dependent strategies for marshalling/unmarshalling operations
 * </p>
 *
 * @param <E> type of Java object, that is an object representation of xml source
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see DomParser
 * @see DomSaver
 */
public class DomMarshaller<E> extends XmlMarshaller<E> {

    /**
     * unmarshalling strategy
     */
    private DomParser<E> parser;

    /**
     * marshalling strategy
     */
    private DomSaver<E> saver;

    /**
     * factory for unmarshalling operation
     */
    private DocumentBuilderFactory builderFactory;

    /**
     * factory for marshalling operation
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * builds {@link Document} object from input stream, validates it and then parses
     * </p>
     *
     * @param xmlStream xml document stream
     * @return parsed object of target type
     * @throws Exception
     */
    @Override
    protected E unmarshallingHook(Reader xmlStream) throws Exception {
        nullCheck(xmlStream);
        Document document = builderFactory.newDocumentBuilder().parse(new InputSource(xmlStream));
        schema.newValidator().validate(new DOMSource(document));
        return parser.parse(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFactory() {
        builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        builderFactory.setNamespaceAware(true);

        transformerFactory = TransformerFactory.newInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @param element
     * @param out
     * @throws Exception if {@link #saver}, element or out param is null, or
     *                   if exception was occur during marshalling operation
     */
    @Override
    public void marshalling(E element, Writer out) throws Exception {
        nullCheck(saver);
        nullCheck(element);
        nullCheck(out);
        Document document = saver.save(element, schema);
        Transformer transformer = configureTransformer(transformerFactory.newTransformer());
        try {
            transformer.transform(new DOMSource(document), new StreamResult(out));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new Exception("marshalling exception", e);
        }
    }

    /**
     * Configures specific transformer object, that is used for
     * marshalling operation.
     *
     * @param transformer object, that service marshalling operation
     * @return configured transformer.
     */
    private Transformer configureTransformer(Transformer transformer) {
        //pretty formatting
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }
}