/**
 * Represents additional information about human.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface HumanAttribute extends Cloneable {

    String getDescription();

    public HumanAttribute clone();
}
