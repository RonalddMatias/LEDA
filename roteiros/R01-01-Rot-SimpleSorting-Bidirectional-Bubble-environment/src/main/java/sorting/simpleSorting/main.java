package sorting.simpleSorting;

import java.util.Arrays;

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
    }
}
