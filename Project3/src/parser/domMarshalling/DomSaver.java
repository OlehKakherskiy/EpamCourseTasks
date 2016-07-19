package parser.domMarshalling;

import org.w3c.dom.Document;

import javax.xml.validation.Schema;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomSaver<E> {
    Document save(E medicines, Schema schema);
}