package parser.unmarshallingResultBuilder;

import javax.xml.stream.XMLStreamConstants;
import java.util.Map;

/**
 * Root of builders hierarchy, that builds target object representation of type specific
 * organised data.
 *
 * @param <E> builder result type
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface UnmarshallingResultBuilder<E> {

    /**
     * Services specific event type with specific parameters.
     *
     * @param stringParam can be tagName or tag text content
     * @param attributes  attributes map, if start element event type was invoked, or empty map otherwise
     * @param eventType   encoded event type of unmarshalling process
     * @see XMLStreamConstants
     */
    void buildPart(Integer eventType, String stringParam, Map<String, String> attributes);

    /**
     * returns parsed result.
     *
     * @return parsed result
     */
    E getResult();
}
