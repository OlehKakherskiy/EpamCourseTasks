package parser.parsingStrategy;

import entity.Certificate;
import entity.Manufacturer;
import entity.TagName;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ManufacturerParser extends AbstractTagParser<Manufacturer> {

    public ManufacturerParser() {
        super(TagName.PRODUCER);
        functionalContext.addInitFunction(TagName.PACKAGES, this::initPackages);
        functionalContext.addInsertDataFunction(TagName.CERTIFICATE, certificate -> element.setCertificate((Certificate) certificate));
        functionalContext.addInsertDataFunction(TagName.PACKAGE, pack -> element.getPackages().add((entity.Package) pack));
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Manufacturer();
        element.setName(getAttributeValue(attributes, TagName.PRODUCER_NAME));
    }

    private void initPackages(Map<String, String> attributes) {
        element.setPackages(new ArrayList<>());
    }


}
