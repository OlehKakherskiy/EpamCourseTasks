package parser;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public enum TagName {

    MEDICINES("medicines"),
    MEDICINE("medicine"),
    PHARM("pharm"),
    GROUP("group"),

    ANALOGUES("analogues"),
    ANALOGUE_ID("analogueID"),

    VERSIONS("versions"),
    REPRESENTATION_TYPES("representationTypes"),
    TYPE("type"),

    CERTIFICATE("certificate"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    REGISTERING_ORGANISATION("registeringOrganisation"),
    CERTIFICATE_ID("certificateID"),

    PACKAGE("package"),
    COUNT("count"),
    CNT("cnt"),
    MEASURE_UNIT("measureUnit"),

    PRICE("price"),
    PACK_TYPE("packType"),
    DOSAGE("dosage"),
    DOSAGE_COUNT("dosageCount"),
    DOSAGE_MEASURE_UNIT("dosageMeasureUnit"),
    TIMES("times"),
    PER("per"),
    FOR("for"),

    NAME("name"),
    ID("ID");


    private String string;

    TagName(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
