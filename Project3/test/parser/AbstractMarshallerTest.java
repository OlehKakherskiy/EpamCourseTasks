package parser;

import entity.Medicines;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.domMarshalling.DefaultDomParser;
import parser.domMarshalling.DefaultDomSaver;
import parser.domMarshalling.DomMarshaller;
import parser.unmarshallingResultBuilder.XmlUnmarshallingResultBuilder;
import parser.unmarshallingResultBuilder.parsingStrategy.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AbstractMarshallerTest {

    static List<AbstractMarshaller<Medicines>> marshallers = new ArrayList<>();

    static StringWriter writer;

    static StringReader nullReader = null;

    static StringBuilder targetFile = new StringBuilder();

    static StringBuilder schemaStringBuilder = new StringBuilder();

    static BufferedReader reader;

    @BeforeClass
    public static void init() throws FileNotFoundException {

        reader = new BufferedReader(new FileReader(new File("test/resources/medicine.xsd")));
        StringBuilder schemaStringBuilder = new StringBuilder();
        reader.lines().forEach(schemaStringBuilder::append);

        BufferedReader targetReader = new BufferedReader(new FileReader("test/resources/testFiles/file.xml"));
        targetReader.lines().forEach(targetFile::append);

        List<AbstractTagParser> tagParsers = new ArrayList<>();
        tagParsers.addAll(Arrays.asList(
                new CertificateParser(),
                new DosageParser(),
                new ManufacturerParser(),
                new MedicineParser(),
                new MedicinesParser(),
                new PackageParser()));

        XmlUnmarshallingResultBuilder<Medicines> unmarshallingResultBuilder = new XmlUnmarshallingResultBuilder<>(tagParsers);

        marshallers.add(new DomMarshaller<>(new StringReader(schemaStringBuilder.toString()), new DefaultDomParser(), new DefaultDomSaver()));
        marshallers.add(new StaxMarshaller<>(new StringReader(schemaStringBuilder.toString()), unmarshallingResultBuilder));
        AbstractMarshaller<Medicines> saxMarshaller = new SaxMarshaller<>(new StringReader(schemaStringBuilder.toString()), unmarshallingResultBuilder, new SaxParser());
        marshallers.add(saxMarshaller);
    }


    @Test
    public void testUnmarshalling() throws Exception {
        List<Medicines> parseResults = new ArrayList<>();
        parseResults.addAll(marshallers.stream().map(
                medicinesAbstractMarshaller -> medicinesAbstractMarshaller.
                        unmarshalling(new StringReader(targetFile.toString()))).collect(Collectors.toList()));

        for (int i = 0; i < parseResults.size(); i++) {
            for (int j = i + 1; j < parseResults.size(); j++) {
                Assert.assertEquals(parseResults.get(i), parseResults.get(j));
            }
        }
    }

    @Test
    public void testMarshalling() throws Exception {

    }
}