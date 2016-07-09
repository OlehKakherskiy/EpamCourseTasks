package parser.domMarshalling;

import entity.Medicines;
import org.w3c.dom.Document;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomParser {

    Medicines parse(Document document);
}
