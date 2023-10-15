package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex != rightIndex && (leftIndex >= 0) && (rightIndex <= array.length) && (array.length > 0)){
			Integer[] sorted = negativeCounting(array, leftIndex, rightIndex);
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = sorted[i];
			}
		}
	}

	

	private Integer[] negativeCounting(Integer[] array, int leftIndex, int rightIndex) {

		// searching the bigger and smaller number
		int biggestNumber = biggestNumber(array, leftIndex, rightIndex);
		int smallestNumber = smallestNumber(array, leftIndex, rightIndex);

		Integer[] assistant = new Integer[biggestNumber - smallestNumber + 1];
		Arrays.fill(assistant,0);


		// calculating the frequency
		for (int i = leftIndex; i <= rightIndex; i++) {
			assistant[array[i] - smallestNumber] += 1;
		}

		// calculating the cumulative
		for (int i = 1; i <= assistant.length-1; i++) {
			assistant[i] = assistant[i] + assistant[i-1];
		}

		Integer[] result = new Integer[array.length];
		Arrays.fill(result, 0);

		// ordering
		for (int i = rightIndex; i >= leftIndex; i--) {
			result[assistant[array[i] - smallestNumber]-1] = array[i];
			assistant[array[i] - smallestNumber] -= 1;
		};
	
		return result;
	}

	private int smallestNumber(Integer[] array, int leftIndex, int rightIndex) {
		int smallestNumber = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if(array[i].compareTo(smallestNumber) < 0){
				smallestNumber = array[i];
			}
		}
		return smallestNumber;
	}

	private int biggestNumber(Integer[] array, int leftIndex, int rightIndex){
		int bigger = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if(array[i].compareTo(bigger) >= 0){
				bigger = array[i];
			}
		}
		return bigger;
	}

}
