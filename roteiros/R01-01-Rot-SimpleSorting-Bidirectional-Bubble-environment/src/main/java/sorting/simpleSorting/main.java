package sorting.simpleSorting;

import java.util.Arrays;

import sorting.variationsOfBubblesort.RecursiveBubbleSort;
import sorting.variationsOfSelectionsort.RecursiveSelectionSort;

public class main {
    public static void main(String[] args) {
        BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>();
        
        Integer[] numeros = new Integer[]{1000, 2423, 9391827, 1, 0};
        bubbleSort.sort(numeros, 0, 0);
        System.out.println(Arrays.toString(numeros));

        InsertionSort<Integer> insertionSort = new InsertionSort<Integer>();
        Integer[] numeros2 = new Integer[]{32, 5, 6, 9};
        insertionSort.sort(numeros2, 0, 0);
        System.out.println(Arrays.toString(numeros2));

        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        Integer[] numeros3 = new Integer[]{77, 2, 5, 2};
        selectionSort.sort(numeros3, 0, 0);
        System.out.println(Arrays.toString(numeros3));
        
        /* 
        RecursiveBubbleSort<Integer> recursiveBubbleSort = new RecursiveBubbleSort<Integer>();
        Integer[] numeros4 = new Integer[]{100, 1, 2,83, 0};
        recursiveBubbleSort.sort(numeros4, 0, numeros4.length-1);
        System.out.println(Arrays.toString(numeros4)); */

        RecursiveSelectionSort<Integer> recursiveSelectionSort = new RecursiveSelectionSort<Integer>();

        Integer[] numeros4 = new Integer[]{5, 2, 5,2};
        recursiveSelectionSort.sort(numeros4, 0, numeros4.length -1);
        System.out.println(Arrays.toString(numeros4));
}
}
