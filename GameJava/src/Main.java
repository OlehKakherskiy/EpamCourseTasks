import controller.Controller;
import model.Model;
import view.View;

import java.io.InputStream;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        InputStream in = System.in;
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view, in);
        controller.playGame();
    }
}
