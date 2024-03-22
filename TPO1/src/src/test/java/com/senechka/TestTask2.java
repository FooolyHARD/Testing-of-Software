package com.senechka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.senechka.task2.MergeSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask2 {
    public static class ArraysForTest{
        final int[] arr;
        final int[] sorted;

        public ArraysForTest(int[] arr, int[] sorted){
            this.arr = arr;
            this.sorted = sorted;
        }
    }
    public static ArraysForTest fillRandomArrayAndSort(int len, boolean onlypositive){
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++){
            if (onlypositive){arr[i] = random.nextInt(100);}
            else {arr[i] = -random.nextInt(100);}
        }
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        return new ArraysForTest(arr, sorted);
    }
    @Test
    @DisplayName("Check random positive values")

    void checkRndSorting() {
        ArraysForTest arraysForTest = fillRandomArrayAndSort(10, true);
        assertAll(
                () -> assertArrayEquals(arraysForTest.sorted, MergeSort.sort(arraysForTest.arr, 0, arraysForTest.arr.length-1))
        );
    }

    @Test
    @DisplayName("Check hardcoded positive values")
    void checkHcSorting(){
        assertAll(
                () -> assertArrayEquals(new int[]{1,3,5,8}, MergeSort.sort(new int[]{8,3,1,5}, 0, 3)),
                () -> assertArrayEquals(new int[]{1,3,5,8,9,17,20,26,28,29,34}, MergeSort.sort(new int[]{8,3,1,5,34,9,17,20,29,26,28}, 0, 10)),
                () -> assertArrayEquals(new int[]{22, 28, 30, 82, 159, 166, 166, 287, 289, 338, 365, 371, 444, 451, 520, 527, 567, 623, 676, 714, 719, 741, 771, 783, 818, 874, 884, 914, 960, 970, 983}, MergeSort.sort(new int[]{444, 520, 166, 874, 676, 22, 338, 783, 365, 970, 623, 30, 287, 451, 914, 371, 741, 818, 527, 714, 166, 159, 960, 289, 567, 82, 771, 983, 719, 884, 28}, 0, 30))
        );
    }

    @Test
    @DisplayName("Check zero-values")
    void checkZeroValues(){
        assertAll(
                () -> assertArrayEquals(new int[]{0,0,0}, MergeSort.sort(new int[]{0,0,0}, 0, 2) )
        );
    }

    @Test
    @DisplayName("Check non-positive random values")
    void checkNonPosRndValues(){
        ArraysForTest arraysForTest = fillRandomArrayAndSort(10, false);
        assertAll(
                () -> assertArrayEquals(arraysForTest.sorted, MergeSort.sort(arraysForTest.arr, 0, arraysForTest.arr.length-1))
        );
    }

    @Test
    @DisplayName("Check non-positive hardcoded values")
    void checkNonPosHcValues(){
        assertAll(
                () -> assertArrayEquals(new int[]{-98, -96, -85, -82, -79, -65, -46, -45, -30, -21}, MergeSort.sort(new int[]{-30, -98, -46, -82, -21, -45, -79, -65, -96, -85}, 0, 9)),
                () -> assertArrayEquals(new int[]{-98, -97, -87, -86, -85, -82, -80, -61, -54, -53, -52, -52, -39, -37, -33, -28, -25, -22, -10, -2}, MergeSort.sort(new int[]{-39, -52, -80, -87, -61, -33, -28, -98, -52, -37, -25, -97, -85, -54, -10, -53, -2, -82, -22, -86}, 0, 19)),
                () -> assertArrayEquals(new int[]{-494, -479, -460, -426, -425, -397, -389, -342, -338, -320, -304, -264, -241, -228, -196, -187, -182, -169, -156, -140, -129, -107, -92, -88, -62, -55, -45, -34, -34, -18}, MergeSort.sort(new int[]{-264, -397, -425, -320, -34, -426, -187, -304, -479, -45, -241, -182, -140, -156, -34, -228, -494, -88, -107, -18, -62, -342, -55, -460, -169, -389, -92, -338, -129, -196}, 0, 29))
        );
    }

    @Test
    @DisplayName("Check empty array")
    void checkEmpty(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> MergeSort.sort(new int[]{}, 0, 10)),
                () -> assertThrows(IllegalArgumentException.class, () -> MergeSort.sort(null, 0, 10))
        );
    }
}

