package org.senechka;

import org.senechka.task1.Arcsin;
import org.senechka.task2.MergeSort;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random random = new Random();
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++){
            arr[i] = -random.nextInt(500);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(MergeSort.sort(arr, 0, arr.length-1)));
    }
}
