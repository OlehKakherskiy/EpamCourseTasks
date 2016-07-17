package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public enum TagName {

    //names for medicine and medicines tags
    MEDICINES("medicines"),
    MEDICINE("medicine"),
    MEDICINE_NAME("name"),
    ID("ID"),
    PHARM("pharm"),
    GROUP("group"),
    ANALOGUES("analogues"),
    ANALOGUE_ID("analogueID"),
    VERSIONS("versions"),
    VERSION("version"),

    //<version> childrens' names
    PRODUCERS("producers"),

    //<producer> children's names
    PRODUCER("producer"),
    PRODUCER_NAME("name"),

    //certificate children's names
    CERTIFICATE("certificate"),
    CERTIFICATE_ID("certificateID"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    REGISTERING_ORGANISATION("registeringOrganisation"),

    TYPE("type"),

    PACKAGES("packages"),

    //package tag childrens
    PACKAGE("package"),
    COUNT("count"),
    MEASURE_UNIT("measureUnit"),
    PRICE("price"),
    PACK_TYPE("packType"),
    REPRESENTATION_TYPE("representationType"),
    DOSAGES("dosages"),
    DOSAGE("dosage"),
    DOSAGE_COUNT("dosageCount"),
    DOSAGE_MEASURE_UNIT("measureUnit"),
    TIMES("times"),
    PER("per"),
    FOR("for");

    private String string;

    TagName(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
    }
