import java.util.Arrays;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CountingSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                countingSort(new int[]{10, 4, 10, 4, 7, 4, 10, 9, 2, 5, 10, 7, 7, 6, 1,
                        7, 3, 3, 5, 5, 9, 3, 2, 9, 8, 1, 4, 10, 9, 7, 7, 9, 3, 3, 8, 10, 9, 8, 7, 9}, 10)));
    }

    private static int[] countingSort(int[] array, int k) {
        for (int anArray : array) {
            if (anArray < 0)
                return null;
        }

        int c[] = new int[k + 1];
        for (int anArray : array) {
            c[anArray]++;
        }
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[--c[array[i]]] = array[i];
        }
        return res;
    }
}