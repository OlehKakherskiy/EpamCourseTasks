package task;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        View v = new View();
        Rectangle rectangle = new Rectangle();
        Controller c = new Controller(v, rectangle);
        c.processUser();
    }
}
