package parser.domMarshalling;

import org.w3c.dom.Document;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomParser<E> {

    E parse(Document document);
}
