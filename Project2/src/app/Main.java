package app;

import java.util.StringTokenizer;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {

        String str = "fgjodfijgiodjfg doifgdoifgnuenrengk dfdgmdfgkmgk,oleh";
        StringTokenizer tokenizer = new StringTokenizer(str, " ,", true);
        while (tokenizer.hasMoreTokens())
            System.out.println(tokenizer.nextElement());

    }
}