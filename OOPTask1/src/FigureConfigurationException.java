/**
 * Exception is thrown if {@link Figure} object has problems with
 * inputted points: it can't be built, using these points, some parameters
 * can't be calculated because of points, this figure is consisted from.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FigureConfigurationException extends Exception {

    /**
     * {@inheritDoc}
     */
    public FigureConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
