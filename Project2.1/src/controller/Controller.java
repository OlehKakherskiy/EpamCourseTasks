package controller;

import app.GlobalContext;
import model.entity.CompositeTextPart;
import view.View;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Controller {

    public void process() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        do {
            View.printMessage((String) GlobalContext.getParam(GlobalContext.INPUT_MESSAGE_KEY));
            String filePath = scanner.nextLine();
            File f = new File(filePath);
            if (f.exists()) {
                flag = true;
            }
            View.printMessage((String) GlobalContext.getParam(GlobalContext.INPUT_LENGTH_KEY));
            String len = scanner.nextLine();
            int length = Integer.parseInt(len);
            try (BufferedReader bis = new BufferedReader(new FileReader(f))) {
                StringBuilder builder = new StringBuilder();
                String buffer = null;
                while ((buffer = bis.readLine()) != null) {
                    builder.append(buffer);
                }
                View.printMessage(Arrays.toString(GlobalContext.TEXT_PART_FACTORY.
                        createCompositeTextPart(CompositeTextPart.class, builder.toString()).findWords(length).toArray()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!flag);


    }
}
