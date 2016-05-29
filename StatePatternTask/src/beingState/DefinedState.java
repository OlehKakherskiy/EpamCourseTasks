package beingState;


/**
 * Marker interface to avoid hierarchical state structure. It's a part
 * of decision to implement states superposition. For example, human can
 * be a fisherman and a mushroomer at the same time. All end-states classes
 * must implement this interface.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public interface DefinedState extends State {
}