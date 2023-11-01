package com.solvd.laba.block1.task1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Quicksort {
    public static int[] createArray(int size){
        int[] array = new int[size];
        Random r = new Random();

        for (int i = 0; i < size; i++){
            array[i] = r.nextInt();
        }
        return array;
    }

    // use quicksort algorithm
    public static void sortArray(int[] array, int begin, int end){
        if (begin < end) {
            int partIndex = partition(array,begin,end);
            sortArray(array,begin,partIndex - 1);
            sortArray(array, partIndex + 1, end);
        }
    }

    private static int partition (int[] array, int begin, int end){
        int pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++){
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array,i+1, end);
        return i + 1;
    }

    private static void swap (int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args){

        System.out.println("Enter length of array");
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = createArray(size);

        System.out.println("Array before sorting: \n" + Arrays.toString(array));

        sortArray(array,0,array.length - 1);
        System.out.println("Sorted array: \n" + Arrays.toString(array));
    }
}
