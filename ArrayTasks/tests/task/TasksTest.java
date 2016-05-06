package task;

import org.junit.Assert;
import org.junit.Test;
import tasks.ArrayElementWrapper;
import tasks.Tasks;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TasksTest {

    @Test
    public void testGetArraySum() throws Exception {
        Assert.assertEquals("Don't work with empty array", 0, Tasks.getArraySum(new int[0]));
        Assert.assertEquals("Don't work with simple array", 2, Tasks.getArraySum(new int[]{-10, 10, 2}));
    }

    @Test
    public void testFindMaxElementAndIndex() throws Exception {
        int[] array = {32, -90, -71, -70, -82, 80, 29, -15, -56, 52, -96, -6, -70, 2, -83,
                -60, 5, -23, 68, -75, 80, 6, -40, 58, 98, -54, -83, 98, 60, 92, 83, -14, -18,
                38, 99, 48, -24, -63, 96, 15, -76, 91, 89, 40, -90, -20, -19, 32, 53, 57, 14,
                -52, -53, 61, -28, -93, -93, 35, 44, 63, 1, -40, -70, 67, 11, -25, 74, -59, 26,
                -15, -67, 96, -88, -50, -28, -96, -63, 15, 25, 2, -13, -60, 34, -35, 39, -4, -63,
                -19, -95, -31, 2, 3, -84, 77, -73, -72, 96, 97, -26, -63};
        Assert.assertEquals("Don't work with empty array", new ArrayElementWrapper(-1, -1), Tasks.findMaxElementAndIndex(new int[0]));
        Assert.assertEquals("Don't do right calcs with array", new ArrayElementWrapper(99, 34), Tasks.findMaxElementAndIndex(array));
        Assert.assertEquals("Don't do right caclcs with array of equalElements", new ArrayElementWrapper(1, 0),
                Tasks.findMaxElementAndIndex(new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testFindMinElementAndIndex() throws Exception {
        int[] array = {32, -90, -71, -70, -82, 80, 29, -15, -56, 52, -96, -6, -70, 2, -83,
                -60, 5, -23, 68, -75, 80, 6, -40, 58, 98, -54, -83, 98, 60, 92, 83, -14, -18,
                38, 99, 48, -24, -63, 96, 15, -76, 91, 89, 40, -90, -20, -19, 32, 53, 57, 14,
                -52, -53, 61, -28, -93, -93, 35, 44, 63, 1, -40, -70, 67, 11, -25, 74, -59, 26,
                -15, -67, 96, -88, -50, -28, -96, -63, 15, 25, 2, -13, -60, 34, -35, 39, -4, -63,
                -19, -95, -31, 2, 3, -84, 77, -73, -72, 96, 97, -26, -63};
        Assert.assertEquals("Don't work with empty array", Tasks.findMinElementAndIndex(new int[0]), new ArrayElementWrapper(-1, -1));
        Assert.assertEquals("Don't do right calcs with array", Tasks.findMinElementAndIndex(array), new ArrayElementWrapper(-96, 10));
        Assert.assertEquals("Don't do right calcs with array of equalElements",
                Tasks.findMaxElementAndIndex(new int[]{1, 1, 1, 1, 1, 1}), new ArrayElementWrapper(1, 0));
    }

    @Test
    public void testFindArrayAverage() throws Exception {
        int[] array = {1, 2, 3, 4, 10};
        Assert.assertEquals("Don't return right value from empty array", 0, Tasks.findArrayAverage(new int[0]));
        Assert.assertEquals("Don't calc right array average", 4, Tasks.findArrayAverage(array));
    }

    @Test
    public void testCountEqualElements() throws Exception {
        int[] array = {32, -90, -71, -70, -82, 80, 29, -15, -56, 52, -96, -6, -70, 2, -83,
                -60, 5, -23, 68, -75, 80, 6, -40, 58, 98, -54, -83, 98, 60, 92, 83, -14, -18,
                38, 99, 48, -24, -63, 96, 15, -76, 91, 89, 40, -90, -20, -19, 32, 53, 57, 14,
                -52, -53, 61, -28, -93, -93, 35, 44, 63, 1, -40, -70, 67, 11, -25, 74, -59, 26,
                -15, -67, 96, -88, -50, -28, -96, -63, 15, 25, 2, -13, -60, 34, -35, 39, -4, -63,
                -19, -95, -31, 2, 3, -84, 77, -73, -72, 96, 97, -26, -63};

        Assert.assertEquals("Should always be 0 equal elements in empty arrays", 0, Tasks.countEqualElements(new int[0], 1));
        Assert.assertEquals("Should be 1 equal elements", 2, Tasks.countEqualElements(array, 80));
        Assert.assertEquals("Should be 0 equal elements", 0, Tasks.countEqualElements(array, 100));
    }

    @Test
    public void testCountBiggerElements() throws Exception {
        int[] array = {32, -90, -71, -70, -82, 80, 29, -15, -56, 52, -96, -6, -70, 2, -83,
                -60, 5, -23, 68, -75, 80, 6, -40, 58, 98, -54, -83, 98, 60, 92, 83, -14, -18,
                38, 99, 48, -24, -63, 96, 15, -76, 91, 89, 40, -90, -20, -19, 32, 53, 57, 14,
                -52, -53, 61, -28, -93, -93, 35, 44, 63, 1, -40, -70, 67, 11, -25, 74, -59, 26,
                -15, -67, 96, -88, -50, -28, -96, -63, 15, 25, 2, -13, -60, 34, -35, 39, -4, -63,
                -19, -95, -31, 2, 3, -84, 77, -73, -72, 96, 97, -26, -63};
        Assert.assertEquals("Should always be 0 bigger elements in empty arrays", 0, Tasks.countBiggerElements(new int[0], 1));
        Assert.assertEquals("Should be 0 bigger elements - this element is max", 0, Tasks.countBiggerElements(array, 99));
        Assert.assertEquals("Should be 8 bigger elements", 8,
                Tasks.countBiggerElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 4, 3, 2, 5, 6}, 3));
    }

    @Test
    public void testTask8() throws Exception {
        Assert.assertArrayEquals("Wrong result with emptyArray", new int[0], Tasks.task8(new int[0], 5));
        Assert.assertArrayEquals("Wrong result with simple array", new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20},
                Tasks.task8(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2));
    }

    @Test
    public void testTask9() throws Exception {
        Assert.assertArrayEquals("Wrong result with emptyArray", new int[0], Tasks.task9(new int[0]));
        Assert.assertArrayEquals("Wrong result with simple array", new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19},
                Tasks.task9(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testTask10() throws Exception {
        Assert.assertArrayEquals("Wrong result with emptyArray", new int[0], Tasks.task10(new int[0]));
        Assert.assertArrayEquals("Wrong result with simple array", new int[]{1, 0, 3, 0, 5, 0, 7, 0, 9, 0, 0, 0, 0, 0},
                Tasks.task10(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 20, 30}));
    }

    @Test
    public void testTask11() throws Exception {
        Assert.assertArrayEquals("Wrong result with emptyArray", new int[0], Tasks.task11(new int[0]));
        Assert.assertArrayEquals("Wrong result with simple array", new int[]{1, 0, 3, 0, 5, 0, 7, 0, 9, 0, 10, 0, 20, 0},
                Tasks.task11(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 20, 30}));
    }

    @Test
    public void testTask12() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", -1, Tasks.task12(new int[0]));
        Assert.assertEquals("Wrong result with simple array", 4, Tasks.task12(new int[]{-1, -2, -3, -4, 1, 2, 3, 4, 5, 6, 7}));
        Assert.assertEquals("Wrong result with finding zero - should return -1", -1, Tasks.task12(new int[]{-1, -2, -3, -4, 0, 0, 0, 0, 0, 0, 0}));

    }

    @Test
    public void testTask13() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", -1, Tasks.task13(new int[0]));
        Assert.assertEquals("Wrong result with simple array", 3, Tasks.task13(new int[]{-1, -2, -3, -4, 1, 2, 3, 4, 5, 6, 7}));
        Assert.assertEquals("Array has only positive values", -1, Tasks.task13(new int[]{1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 0, 0}));
    }

    @Test
    public void testTask14() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", 0, Tasks.task14(new int[0], 14));
        Assert.assertEquals("Wrong result with simple array", 3, Tasks.task14(new int[]{-1, -2, -3, -4, 1, 2, 1, 4, 1, 6, 7}, 1));
        Assert.assertEquals("Wrong result - should be returned 0", 0, Tasks.task14(new int[]{-1, -2, -3, -4, -5, 15, 20}, 25));
    }

    @Test
    public void testTask15() throws Exception {
        Assert.assertArrayEquals("Wrong result with emptyArray", new int[0], Tasks.task15(new int[0], 14));
        Assert.assertArrayEquals("Wrong result with simple array", new int[]{1, 4, 6},
                Tasks.task15(new int[]{1, -1, 2, 3, -1, 5, -1, 7, 8, 9, 10}, -1));
        Assert.assertArrayEquals("Should be empty result array", new int[0],
                Tasks.task15(new int[]{1, -1, 2, 3, -1, 5, -1, 7, 8, 9, 10}, 0));
    }

    @Test
    public void testTask16() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", true, Tasks.task16(new int[0]));
        Assert.assertEquals(true, Tasks.task16(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        Assert.assertEquals(false, Tasks.task16(new int[]{1, 2, 3, 3, 4, 5, 7, 7, 9, 9, 0}));
        Assert.assertEquals(true, Tasks.task16(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}));
    }

    @Test
    public void testTask17() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", true, Tasks.task17(new int[0]));
        Assert.assertEquals(true, Tasks.task17(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -100}));
        Assert.assertEquals(false, Tasks.task17(new int[]{9, 8, 7, 6, 10, 5, 11, 3, 2, 2, 1}));
        Assert.assertEquals(true, Tasks.task17(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}));
    }

    @Test
    public void testTask18() throws Exception {
        Assert.assertArrayEquals(new int[0], Tasks.task18(new int[0], 5));
        Assert.assertArrayEquals(new int[]{1, 2, 3}, Tasks.task18(new int[]{1, 2, 3}, 0));
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8, 1, 2}, Tasks.task18(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 6));
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8, 1, 2}, Tasks.task18(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, -2));
    }

    @Test
    public void testTask19() throws Exception {
        Assert.assertArrayEquals(new int[0], Tasks.task19(new int[0]));
        Assert.assertArrayEquals(new int[]{1, 7}, Tasks.task19(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 1}));
        Assert.assertArrayEquals(new int[]{1}, Tasks.task19(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }


    @Test
    public void testTask21() throws Exception {
        Assert.assertArrayEquals(new int[0], Tasks.task21(new int[0]));
        Assert.assertArrayEquals(new int[]{1, 3, 4, 7}, Tasks.task21(new int[]{1, 2, 3, 4, 5, 6, 7, 2, 5, 6}));
        Assert.assertArrayEquals(new int[]{}, Tasks.task21(new int[]{1, 2, 3, 3, 4, 5, 6, 6, 5, 3, 4, 2, 1}));
    }

    @Test
    public void testTask22() throws Exception {
        Assert.assertArrayEquals(new int[0], Tasks.task22(new int[0], new int[]{1, 2, 3, 4}));
        Assert.assertArrayEquals(new int[]{2, 54, 76}, Tasks.task22(new int[]{2, 54, 76}, new int[0]));
        Assert.assertArrayEquals(new int[]{1, 2, 8}, Tasks.task22(new int[]{1, 2, 3, 10, 15, 20, 11, 8}, new int[]{3, 15, 10, 11, 20}));
        Assert.assertArrayEquals(new int[]{}, Tasks.task22(new int[]{1, 2, 3, 4, 5, 6, 7, 10, 9, -1}, new int[]{3, 2, 1, 4, 7, 5, 6, -1, 9, 10}));
    }

    @Test
    public void testTask23() throws Exception {
        Assert.assertEquals("Wrong result with emptyArray", 0, Tasks.task23(new int[0]));
        Assert.assertEquals("Wrong result with simple array", 3, Tasks.task23(new int[]{-1, -2, -3, -4, -1, 2, -1, 4, -1, 6, 7}));
        Assert.assertEquals("Wrong result - should be returned 0", 0, Tasks.task23(new int[]{-1, -2, -3, -4, -5, 15, 20}));
    }

    @Test
    public void testTask24() throws Exception {
        Assert.assertArrayEquals(new int[0], Tasks.task24(new int[0], new int[0]));
        Assert.assertArrayEquals(new int[]{1, 4, 23}, Tasks.task24(new int[]{1, 4, 23}, new int[0]));
        Assert.assertArrayEquals(new int[]{1, 4, 23, 40}, Tasks.task24(new int[0], new int[]{1, 4, 23, 40}));
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 20, 22, 30},
                Tasks.task24(new int[]{1, 2, 3, 4, 5, 6, 10, 20, 30}, new int[]{7, 8, 9, 10, 11, 12, 22}));
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8, 8, 10, 12, 20},
                Tasks.task24(new int[]{1, 2, 3, 8}, new int[]{4, 4, 5, 6, 7, 8, 10, 12, 20}));
    }

    @Test
    public void testTask25() throws Exception {
        Assert.fail();
    }
}