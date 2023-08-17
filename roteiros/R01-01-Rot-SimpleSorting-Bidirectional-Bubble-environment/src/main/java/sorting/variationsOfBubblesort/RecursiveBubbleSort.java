package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex != rightIndex && (leftIndex >= 0) && (rightIndex <= array.length) && (array.length > 0)){
			if(array[leftIndex].compareTo(array[leftIndex+1]) > 0){
				Util.swap(array, leftIndex, leftIndex+1);
			}
			System.out.println(Arrays.toString(array));
			System.out.println();
			sort(array, leftIndex+1, rightIndex);
			sort(array, leftIndex, rightIndex-1);
		}
		
	}


}
