package tasks;

import java.util.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Tasks {

    //Найти сумму элементов массива
    public static long getArraySum(int[] array) throws IllegalArgumentException {
        nullCheck(array);
        long result = 0;
        for (int elem : array) {
            result += elem;
        }
        return result;
    }

    //Найти максимальный элемент, значение и индекс
    public static ArrayElementWrapper findMaxElementAndIndex(int[] array) {
        nullCheck(array);
        if (array.length == 0)
            return new ArrayElementWrapper(-1, -1);
        int maxElemIndex = 0;
        for (int i = 1; i < array.length; i++) {
            maxElemIndex = (array[maxElemIndex] < array[i]) ? i : maxElemIndex;
        }
        return new ArrayElementWrapper(array[maxElemIndex], maxElemIndex);
    }

    //Найти минимальный элемент, значение и индекс
    public static ArrayElementWrapper findMinElementAndIndex(int[] array) {
        nullCheck(array);
        if (array.length == 0)
            return new ArrayElementWrapper(-1, -1);
        int minElementIndex = 0;
        for (int i = 1; i < array.length; i++) {
            minElementIndex = (array[minElementIndex] > array[i]) ? i : minElementIndex;
        }
        return new ArrayElementWrapper(array[minElementIndex], minElementIndex);
    }

    //Найти среднее значение элементов массива
    public static int findArrayAverage(int[] array) {
        nullCheck(array);
        int result = 0;
        if (array.length == 0)
            return 0;
        for (int element : array) {
            result += element;
        }
        return result / array.length;
    }

    //Посчитать количество элементов равных заданному (и нулю)
    public static int countEqualElements(int[] array, int element) {
        nullCheck(array);
        int result = 0;
        for (int anArray : array) {
            result = (anArray == element) ? (result + 1) : result;
        }
        return result;
    }

    //Посчитать количество элементов больше нуля
    public static int countBiggerElements(int[] array, int element) {
        nullCheck(array);
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = (array[i] > element) ? (result + 1) : result;
        }
        return result;
    }

    //умножить элементы массива на число
    public static int[] task8(int[] array, int number) {
        nullCheck(array);
        if (array.length == 0)
            return array;
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] * number;
        }
        return result;
    }

    //прибавить к элементам массива их индексы
    public static int[] task9(int[] array) {
        nullCheck(array);
        if (array.length == 0)
            return array;
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] + i;
        }
        return result;
    }

    //обнулить четные элементы
    public static int[] task10(int[] array) {
        nullCheck(array);

        if (array.length == 0)
            return array;
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (array[i] % 2) == 0 ? 0 : array[i];
        }
        return result;
    }

    //обнулить с нечетным индексом
    public static int[] task11(int[] array) {
        nullCheck(array);
        for (int i = 1; i < array.length; i += 2) {
            array[i] = 0;
        }
        return array;
    }

    //найти первый положительный
    public static int task12(int[] array) {
        nullCheck(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //найти последний отрицательный
    public static int task13(int[] array) {
        nullCheck(array);
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] < 0)
                return i;
        }
        return -1;
    }

    //количество элементов, равных заданному
    public static int task14(int[] array, int elem) {
        nullCheck(array);
        int count = 0;
        for (int anArray : array) {
            count = (anArray == elem) ? (count + 1) : count;
        }
        return count;
    }

    //вхождения элемента в массив
    public static int[] task15(int[] array, int elem) {
        nullCheck(array);
        if (array.length == 0)
            return new int[0];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elem) {
                result.add(i);
            }
        }
        return listToArray(result);
    }

    //проверить на упорядоченность по возростанию
    public static boolean task16(int[] array) {
        nullCheck(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //проверить на упорядоченность по убыванию
    public static boolean task17(int[] array) {
        nullCheck(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //циклический сдвиг
    public static int[] task18(int[] array) {

        return null;
    }

    //вывести значения элементов, равных другим
    public static int[] task19(int[] array) {
        Set<Integer> dublicates = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int anArray : array) {
            if (!set.add(anArray)) {
                dublicates.add(anArray);
            }
        }
        return listToArray(new ArrayList<>(dublicates));
    }

    //количество элементов, больших среднего значения массива
    public static int task20(int[] array) {
        nullCheck(array);
        int average = findArrayAverage(array);
        return countBiggerElements(array, average);
    }

    //вывести значения элементов, не имеющих дубликатов
    public static int[] task21(int[] array) {
        Set<Integer> resultSet = new HashSet<>();
        Set<Integer> duplicateSet = new HashSet<>();
        for (int anArray : array) {
            if (!resultSet.add(anArray)) {
                duplicateSet.add(anArray);
            }
        }
        resultSet.removeAll(duplicateSet);
        return listToArray(new ArrayList<>(resultSet));
    }

    //вывести значения из первого массива, не имеющих дубликатов во втором
    public static int[] task22(int[] array1, int[] array2) {
        nullCheck(array1);
        nullCheck(array2);
        if (array1.length == 0) {
            return new int[0];
        }
        if (array2.length == 0) {
            int[] result = new int[array1.length];
            System.arraycopy(array1, 0, result, 0, array1.length);
            System.out.println(Arrays.toString(result));
            return result;
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int e : array2) {
            set.add(e);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int anArray1 : array1) {
            if (set.add(anArray1)) { //Set устраняет дубликаты. Если элемент добавился, значит он не является дубликатом
                resultList.add(anArray1);
            }
        }
        return listToArray(resultList);
    }

    //количество элементов, равных первому
    public static int task23(int[] array) {
        nullCheck(array);
        if (array.length == 0) {
            return 0;
        }
        int result = task14(array, array[0]);
        return (result > 0) ? result - 1 : result;
    }

    //слить в один массив отсортированные массивы, не сортируя
    public static int[] task24(int[] array1, int[] array2) {
        nullCheck(array1);
        nullCheck(array2);

//        if (array1.length == 0)
//            return array2;
//        if (array2.length == 0)
//            return array1;

        int[] result = new int[array1.length + array2.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < array1.length && rightIndex < array2.length) { //merge
            result[resultIndex++] = (array1[leftIndex] <= array2[rightIndex]) ? array1[leftIndex++] : array2[rightIndex++];
        }
        if (leftIndex >= array1.length) {
            System.arraycopy(array2, rightIndex, result, resultIndex, array2.length - rightIndex);
        } else {
            System.arraycopy(array1, leftIndex, result, resultIndex, array1.length - leftIndex);
        }
        return result;
    }


    public static int task25(int[] array) {
        return -1;
    }

    private static void nullCheck(int[] array) throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("Shouldn't be null");
        }
    }

    private static int[] listToArray(List<Integer> list) {
        int[] result = new int[list.size()];
        int i = 0;
        for (int e : list) {
            result[i] = e;
            i++;
        }
        return result;
    }
}
