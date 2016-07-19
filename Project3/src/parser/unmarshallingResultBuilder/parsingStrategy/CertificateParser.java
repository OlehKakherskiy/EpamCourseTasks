package parser.unmarshallingResultBuilder.parsingStrategy;

import entity.Certificate;
import entity.TagName;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CertificateParser extends AbstractTagParser<Certificate> {

    public CertificateParser() {
        super(TagName.CERTIFICATE);
    }

    @Override
    protected void initFunctionalContext() {
        functionalContext.addInsertDataFunction(TagName.START_DATE, date -> element.setStartDate(LocalDate.parse((CharSequence) date)));
        functionalContext.addInsertDataFunction(TagName.END_DATE, date -> element.setEndDate(LocalDate.parse((CharSequence) date)));
        functionalContext.addInsertDataFunction(TagName.REGISTERING_ORGANISATION, regOrg -> element.setRegisteringOrganisation((String) regOrg));
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Certificate();
        element.setCertificateID(getAttributeValue(attributes, TagName.CERTIFICATE_ID));
    }
}