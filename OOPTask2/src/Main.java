import java.time.LocalDate;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        Human human = new Human("Oleh", "Ihor", "Kakherskyi", LocalDate.of(1995, 5, 11), Sex.MALE, "Address",
                new Education("bachelor's degree", "KPI"),
                new JobPosition("position", "posDescription", 100500, LocalDate.of(2000, 5, 11)));

        System.out.println(human.toString());

    }
}
