package parser.domMarshalling;

import org.w3c.dom.Document;

/**
 * Encapsulates parsing strategy of specific {@link Document} to
 * Java object of type {@link E}
 *
 * @param <E> target type, to which will be parsed {@link Document} object
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomParser<E> {

    E parse(Document document);
}
