package parser;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.nio.file.NoSuchFileException;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractMarshaller {

    public Medicines unmarshalling(String xmlFilePath, String xmlSchemaPath) throws Exception {
        nullAndEmptinessCheck(xmlFilePath);
        nullAndEmptinessCheck(xmlSchemaPath);
        try {
            Schema schema = getDocumentSchema(xmlSchemaPath);
            Source source = getSource(xmlFilePath);
            schema.newValidator().validate(source);
            return unmarshalling(source);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("xml file with path: " + xmlFilePath + " " +
                    "isn't valid according to schema with path: " + xmlSchemaPath);
        }
    }

    public abstract void marshalling(Medicines medicines, String fileName) throws Exception;

    protected abstract Source getSource(String xmlFilePath) throws Exception;

    protected abstract Medicines unmarshalling(Source source);


    private Schema getDocumentSchema(String xmlSchemaPath) throws NoSuchFileException, SAXException {
        File f = getFile(xmlSchemaPath);
        return SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(f);
    }

    protected void nullAndEmptinessCheck(String string) {
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException("file path is null or empty");
    }

    protected File getFile(String string) throws NoSuchFileException {
        File f = new File(string);
        if (!f.exists()) {
            throw new NoSuchFileException("No file with path: " + string);
        }
        return f;
    }
}
