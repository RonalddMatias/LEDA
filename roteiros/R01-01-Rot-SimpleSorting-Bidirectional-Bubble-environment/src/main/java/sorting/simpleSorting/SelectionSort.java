package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if((leftIndex >= 0) && (rightIndex <= array.length) && (array.length > 0)){
			int menorIndice = 0;
			for(int i = 0; i < array.length; i++){
			menorIndice = i;
				for(int j = i + 1; j < array.length; j++){
					if(array[j].compareTo(array[i]) < 0){
						menorIndice = j;
				}
			}

			Util.swap(array, menorIndice, i);
		}
		}
		
	}
}
