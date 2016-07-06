import java.util.Arrays;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class MergeSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{7, 65, 26, 2, 45, 90, 26, 27, 48, 32, 80, 10, 99, 34, 48, 60, 95, 34, 95, 72, 42, 69, 85,
                69, 26, 68, 84, 81, 57, 68, 33, 52, 59, 33, 63, 4, 69, 44, 40, 59, 6, 69, 24, 86, 16, 31, 63, 99, 62,
                84, 47, 79, 22, 7, 31, 86, 32, 90, 3, 69, 22, 51, 16, 42, 25, 2, 41, 36, 79, 35, 84, 93, 99, 88, 17,
                41, 15, 34, 12, 89, 40, 56, 39, 85, 26, 16, 23, 66, 78, 27, 20, 45, 49, 53, 83, 66, 37, 18, 15, 7})));
    }

    public static int[] mergeSort(int[] array) {
        return mergeSort(array, 0, array.length - 1);
    }


    private static int[] mergeSort(int[] array, int start, int end) {
        if (start == end)
            return new int[]{array[start]};
        int mid = (start + end) / 2;
        int[] left = mergeSort(array, start, mid);
        int[] right = mergeSort(array, mid + 1, end);
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int l = 0, r = 0, res = 0;
        int[] result = new int[left.length + right.length];
        while (l < left.length && r < right.length) {
            result[res++] = (left[l] <= right[r]) ? left[l++] : right[r++];
        }
        System.arraycopy(right, r, result, res, right.length - r);
        System.arraycopy(left, l, result, res, left.length - l);
        System.out.println(Arrays.toString(result));
        return result;
    }
}