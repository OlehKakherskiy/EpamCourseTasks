package parser.domMarshalling;

import entity.Medicine;
import entity.Medicines;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DefaultDomSaver implements DomSaver {

    @Override
    public Document save(Medicines medicines) {
        return null;
    }

    private Node processMedicine(Medicine medicine) {
        return null;
    }
}