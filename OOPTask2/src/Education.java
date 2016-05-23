/**
 * Class represents education information of a human.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Education implements HumanAttribute {

    private String documentNumber;

    private String highSchoolName;

    public Education(String documentNumber, String highSchoolName) {
        this.documentNumber = documentNumber;
        this.highSchoolName = highSchoolName;
    }


    @Override
    public String getDescription() {
        return toString();
    }


    @Override
    public String toString() {
        return "Education{" +
                "documentNumber='" + documentNumber + '\'' +
                ", highSchoolName='" + highSchoolName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education)) return false;

        Education education = (Education) o;

        return documentNumber.equals(education.documentNumber) &&
                highSchoolName.equals(education.highSchoolName);

    }

    @Override
    public int hashCode() {
        int result = documentNumber.hashCode();
        result = 31 * result + highSchoolName.hashCode();
        return result;
    }

    @Override
    public Education clone() {
        return new Education(new String(documentNumber), new String(highSchoolName));
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getHighSchoolName() {
        return highSchoolName;
    }
}
