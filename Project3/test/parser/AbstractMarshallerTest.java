package parser;

import entity.Medicines;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.domMarshalling.DefaultDomParser;
import parser.domMarshalling.DefaultDomSaver;
import parser.domMarshalling.DomMarshaller;

import java.io.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AbstractMarshallerTest {

    static AbstractMarshaller<Medicines>[] marshallers;

    static StringWriter writer;

    static StringReader nullReader = null;

    static BufferedReader reader;

    @BeforeClass
    public static void init() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(new File("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project3\\test\\resources\\medicine.xsd")));

        marshallers = new AbstractMarshaller[1];
        marshallers[0] = new DomMarshaller(reader, new DefaultDomParser(), new DefaultDomSaver());

    }


    @Test
    public void mainTest() throws Exception {
        for (AbstractMarshaller<Medicines> marshaller : marshallers) {
            Medicines medicines = marshaller.unmarshalling(new BufferedReader(new FileReader("D:\\Документы\\FICT\\Новая папка\\EpamTasks\\Project3\\test\\resources\\testFiles\\file.xml")));
            System.out.println();
            writer = new StringWriter();
            marshaller.marshalling(medicines, writer);
            System.out.println(writer.getBuffer().toString());
        }
    }

    @Test
    public void marshalling() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullXmlStream() throws Exception {
        marshallers[0].unmarshalling(nullReader);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullOutputStream() throws Exception {
        marshallers[0].marshalling(new Medicines(), null);
    }

}