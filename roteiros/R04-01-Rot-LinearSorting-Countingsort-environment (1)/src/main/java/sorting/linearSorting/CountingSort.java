package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if((leftIndex != rightIndex) && (leftIndex >= 0) && (rightIndex <= array.length) && (array.length > 0)){
			Integer[] sorted = counting(array, leftIndex, rightIndex);
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = sorted[i];
			}
		}
	}

	private Integer[] counting(Integer[] array, int leftIndex, int rightIndex) {
		int biggerIndex = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo(biggerIndex) > 0){
				biggerIndex = array[i];
			}
		}

		Integer[] assistant = new Integer[biggerIndex + 1];
		Arrays.fill(assistant, 0);

		// calculating the frequency
		for(int i = leftIndex; i <= rightIndex; i++){
			assistant[array[i]] += 1;
		}
		System.out.println(Arrays.toString(assistant));

		// calculating the cumulative
		for (int i = 1; i <= assistant.length-1; i++) {
			assistant[i] = assistant[i] + assistant[i-1];
		}
		System.out.println(Arrays.toString(assistant));
		
		Integer[] result = new Integer[array.length];
		Arrays.fill(result, 0);

		// ordering
		for(int i = rightIndex; i >= leftIndex; i--){
			result[assistant[array[i]]-1] = array[i];
			System.out.println(Arrays.toString(result));
			assistant[array[i]] -= 1; 
		}

		return result;
	}


}
