import java.util.Arrays;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class InsertionSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertionSort(new int[]{7, 65, 26, 2, 45, 90, 26, 27, 48, 32, 80, 10, 99, 34, 48, 60, 95, 34, 95, 72, 42, 69, 85,
                69, 26, 68, 84, 81, 57, 68, 33, 52, 59, 33, 63, 4, 69, 44, 40, 59, 6, 69, 24, 86, 16, 31, 63, 99, 62,
                84, 47, 79, 22, 7, 31, 86, 32, 90, 3, 69, 22, 51, 16, 42, 25, 2, 41, 36, 79, 35, 84, 93, 99, 88, 17,
                41, 15, 34, 12, 89, 40, 56, 39, 85, 26, 16, 23, 66, 78, 27, 20, 45, 49, 53, 83, 66, 37, 18, 15, 7})));
    }

    private static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int buf = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > buf) {
                    array[j + 1] = array[j];
                    array[j] = buf;
                } else {
                    break;
                }
            }
        }
        return array;
    }
}