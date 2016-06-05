package controller.command;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface IOperationCommand<T, E> {

    T processCommand(E params);
}
