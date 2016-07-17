package parser.domMarshalling;

import entity.Medicines;
import org.w3c.dom.Document;

import javax.xml.validation.Schema;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface DomSaver {
    Document save(Medicines medicines, Schema schema);
}