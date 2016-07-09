package parser.domMarshalling;

import org.w3c.dom.Document;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomSaver {
    Document save(Medicines medicines);
}