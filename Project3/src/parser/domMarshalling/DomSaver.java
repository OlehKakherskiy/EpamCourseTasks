package parser.domMarshalling;

import org.w3c.dom.Document;

import javax.xml.validation.Schema;

/**
 * Encapsulates marshalling strategy of specific object of specific type
 * to xml, which structure is represented in xml schema parameter
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomSaver<E> {

    /**
     * @param medicines source object, that is need to be marshaled to xml
     * @param schema    xml schema, to which structure object will be mapped
     * @return Document object, that will be saved to specific storage
     */
    Document save(E medicines, Schema schema);
}