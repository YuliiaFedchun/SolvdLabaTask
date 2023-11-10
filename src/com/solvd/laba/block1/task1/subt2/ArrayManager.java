package com.solvd.laba.block1.task1.subt2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArrayManager {
    public static int[] createArray(int size) {
        int[] array = new int[size];
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt();
        }
        return array;
    }

    public static void main(String[] args) {

        System.out.println("Enter length of array");
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = createArray(size);

        System.out.println("Array before sorting: \n" + Arrays.toString(array));

        QuickSort.sortArray(array, 0, array.length - 1);
        System.out.println("Sorted array: \n" + Arrays.toString(array));
    }
}
