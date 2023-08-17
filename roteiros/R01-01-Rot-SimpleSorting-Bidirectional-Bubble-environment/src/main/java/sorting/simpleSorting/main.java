package sorting.simpleSorting;

import java.util.Arrays;

import sorting.variationsOfBubblesort.BidirectionalBubbleSort;
import sorting.variationsOfBubblesort.RecursiveBubbleSort;
import sorting.variationsOfSelectionsort.RecursiveSelectionSort;

public class main {
    public static void main(String[] args) {

        //BidirectionalBubbleSort<Integer> bidirectionalBubbleSort = new BidirectionalBubbleSort<Integer>();
        //Integer[] num = new Integer[]{10,2,4,6,0};
        //bidirectionalBubbleSort.sort(num, 0, num.length-1);
        //System.out.println(Arrays.toString(num));

        //BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>();
        
        //Integer[] numeros = new Integer[]{};
        //bubbleSort.sort(numeros, 0,numeros.length-1);
        //System.out.println(Arrays.toString(numeros));


        
        //InsertionSort<Integer> insertionSort = new InsertionSort<Integer>();
        //Integer[] numeros2 = new Integer[]{32, 5, 6, 9};
        //insertionSort.sort(numeros2, -1, 0);
        //System.out.println(Arrays.toString(numeros2));

        
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        Integer[] numeros3 = new Integer[]{77, 2, 5, 2};
        selectionSort.sort(numeros3, 0, 0);
        System.out.println(Arrays.toString(numeros3));

        
      //  RecursiveBubbleSort<Integer> recursiveBubbleSort = new RecursiveBubbleSort<Integer>();
       // Integer[] numeros4 = new Integer[]{100, 1, 2,83, 0};
        //recursiveBubbleSort.sort(numeros4, 0, numeros4.length-1);
        //System.out.println(Arrays.toString(numeros4)); 

        //RecursiveSelectionSort<Integer> recursiveSelectionSort = new RecursiveSelectionSort<Integer>();

        //Integer[] numeros5 = new Integer[]{5,3,16,0,1};
        //recursiveSelectionSort.sort(numeros5, 0, numeros5.length -1);
        //System.out.println(Arrays.toString(numeros5));
}
}
