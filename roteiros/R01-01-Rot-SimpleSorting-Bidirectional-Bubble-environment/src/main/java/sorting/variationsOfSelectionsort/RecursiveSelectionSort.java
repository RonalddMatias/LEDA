package sorting.variationsOfSelectionsort;

import java.lang.reflect.Array;
import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			int m = menor(array, leftIndex, rightIndex);
			Util.swap(array, leftIndex, m);
			System.out.println(Arrays.toString(array));
			sort(array, leftIndex+1, rightIndex);
		}	

			
			
		}

	public int menor(T[] array, int leftIndex, int rightIndex){
		int menor = 0;
		int out = leftIndex;
		if(leftIndex < rightIndex){
			out = menor(array,leftIndex +1,rightIndex);
		}
		if(array[out].compareTo(array[leftIndex]) < 0){
			menor = out;
		} else {
			menor = leftIndex;
		}

		return menor;
	}

}
 