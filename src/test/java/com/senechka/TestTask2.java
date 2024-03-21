package com.senechka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.senechka.task2.MergeSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask2 {
    public static class ArraysForTest{
        final int[] arr;
        final int[] sorted;

        public ArraysForTest(int[] arr, int[] sorted){
            this.arr = arr;
            this.sorted = sorted;
        }
    }
    public static ArraysForTest fillRandomArrayAndSort(int len){
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++){
            arr[i] = random.nextInt(100);
        }
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        return new ArraysForTest(arr, sorted);
    }
    @Test
    @DisplayName("Check random positive values")

    void checkRndSorting() {
        ArraysForTest arraysForTest = fillRandomArrayAndSort(10);
        assertAll(
                () -> assertArrayEquals(arraysForTest.sorted, MergeSort.sort(arraysForTest.arr, 0, arraysForTest.arr.length-1))
        );
    }

    @Test
    @DisplayName("Chech hardcoded positive values")
    void checkHcSorting(){
        assertAll(
                () -> assertArrayEquals(new int[]{1,3,5,8}, MergeSort.sort(new int[]{8,3,1,5}, 0, 3)),
                () -> assertArrayEquals(new int[]{1,3,5,8,9,17,20,26,28,29,34}, MergeSort.sort(new int[]{8,3,1,5,34,9,17,20,29,26,28}, 0, 10)),

                () -> assertArrayEquals(new int[]{22, 28, 30, 82, 159, 166, 166, 287, 289, 338, 365, 371, 444, 451, 520, 527, 567, 623, 676, 714, 719, 741, 771, 783, 818, 874, 884, 914, 960, 970, 983}, MergeSort.sort(new int[]{444, 520, 166, 874, 676, 22, 338, 783, 365, 970, 623, 30, 287, 451, 914, 371, 741, 818, 527, 714, 166, 159, 960, 289, 567, 82, 771, 983, 719, 884, 28}, 0, 30))

        );
    }
}

