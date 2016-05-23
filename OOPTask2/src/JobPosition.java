import java.time.LocalDate;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class JobPosition implements HumanAttribute {

    private String jobPositionName;

    private String jobDescription;

    private double salary;

    private LocalDate hiringDate;

    public JobPosition(String jobPositionName, String jobDescription, double salary, LocalDate hiringDate) {
        this.jobPositionName = jobPositionName;
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.hiringDate = hiringDate;
    }

    @Override
    public String getDescription() {
        return toString();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobPositionName() {
        return jobPositionName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public LocalDate getHiringDate() {
        return LocalDate.of(hiringDate.getYear(), hiringDate.getMonthValue(), hiringDate.getDayOfMonth());
    }

    @Override
    public String toString() {
        return "JobPosition{" +
                "jobPositionName='" + jobPositionName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", salary=" + salary +
                ", hiringDate=" + hiringDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobPosition)) return false;

        JobPosition that = (JobPosition) o;

        return (Double.compare(that.salary, salary) == 0) &&
                jobPositionName.equals(that.jobPositionName) &&
                jobDescription.equals(that.jobDescription) &&
                hiringDate.equals(that.hiringDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = jobPositionName.hashCode();
        result = 31 * result + jobDescription.hashCode();
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + hiringDate.hashCode();
        return result;
    }

    @Override
    public JobPosition clone() {
        return new JobPosition(jobPositionName, jobDescription, salary, getHiringDate());
    }
}
