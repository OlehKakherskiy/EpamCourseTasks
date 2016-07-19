package parser;

import parser.unmarshallingResultBuilder.UnmarshallingResultBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * Class is a root of marshalling hierarchy. It's used for marshalling/unmarshalling any type
 * of documents from/to different i/o streams.
 *
 * @param <E> java type, mapped to specific type located in stream
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractMarshaller<E> {

    protected UnmarshallingResultBuilder<E> resultBuilder;

    public AbstractMarshaller(UnmarshallingResultBuilder<E> resultBuilder) {
        this.resultBuilder = resultBuilder;
    }

    /**
     * Maps information, represented in type dependent format, to object representation
     *
     * @param input information source
     * @return object representation of
     */
    public abstract E unmarshalling(Reader input);

    /**
     * Maps object presented data to specific type format.
     *
     * @param element source element, that will be marshaled
     * @param out target source
     * @throws Exception if any type of exception will occur during process
     */
    public abstract void marshalling(E element, Writer out) throws Exception;

    /**
     * null check, throws {@link IllegalArgumentException}
     *
     * @param stream any object
     */
    protected void nullCheck(Object stream) {
        if (stream == null) {
            throw new IllegalArgumentException("stream is null");
        }
    }
}