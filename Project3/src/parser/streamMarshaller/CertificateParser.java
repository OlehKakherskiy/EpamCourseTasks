package parser.streamMarshaller;

import entity.Certificate;
import entity.TagName;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CertificateParser extends AbstractTagParser<Certificate> {

    public CertificateParser(TagName tagName) {
        super(tagName);
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Certificate();
        element.setCertificateID(getAttributeValue(attributes, TagName.CERTIFICATE_ID));
        element.setStartDate(LocalDate.parse(getAttributeValue(attributes, TagName.START_DATE)));
        element.setEndDate(LocalDate.parse(getAttributeValue(attributes, TagName.END_DATE)));
        element.setRegisteringOrganisation(getAttributeValue(attributes, TagName.REGISTERING_ORGANISATION));
    }
}