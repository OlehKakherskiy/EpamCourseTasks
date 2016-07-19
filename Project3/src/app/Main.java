package app;

import entity.Medicines;
import parser.AbstractMarshaller;
import parser.domMarshalling.DefaultDomParser;
import parser.domMarshalling.DefaultDomSaver;
import parser.domMarshalling.DomMarshaller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringWriter;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader targetFileReader = new BufferedReader(new FileReader("Project3/src/file.xml"));
        StringWriter result = new StringWriter();
        BufferedReader schemaReader = new BufferedReader(new FileReader("Project3/src/medicine.xsd"));


        AbstractMarshaller<Medicines> domMarshaller =
                new DomMarshaller<Medicines>(schemaReader, new DefaultDomParser(), new DefaultDomSaver());

        domMarshaller.marshalling(domMarshaller.unmarshalling(targetFileReader), result);

        System.out.println(result.getBuffer().toString());
    }
}
