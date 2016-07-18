package parser.streamMarshaller;

import entity.Medicines;
import parser.AbstractMarshaller;

import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import java.io.Reader;
import java.util.Stack;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class StreamMarshaller extends AbstractMarshaller<Medicines> {

    private FunctionalContext functionalContext;

    private Stack<FunctionalContext> functionalContextStack;


    public StreamMarshaller(Reader xmlSchemaReader, FunctionalContext functionalContext) {
        super(xmlSchemaReader);
        this.functionalContext = functionalContext;
        functionalContextStack = new Stack<>();
    }

    public StreamMarshaller(Schema schema, FunctionalContext functionalContext) {
        super(schema);
        this.functionalContext = functionalContext;
        functionalContextStack = new Stack<>();
    }

    public abstract void startParsing(XMLEvent event);
}
