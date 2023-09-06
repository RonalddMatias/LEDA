package orderStatistic;

import java.util.Arrays;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		T[] Kestatisticas = (T[]) new Comparable[0];
		if((array.length > 0) && (k >= 1) && (array != null) && (k <= array.length)){
			Kestatisticas = Arrays.copyOfRange(Kestatisticas, 0, k);
			for(int i = k; i>0; i--){
				Kestatisticas[i-1] = orderStatistics(array, k);
				k--;
			}
		}
		return Kestatisticas;	
		//este metodo deve obrigatoriamente usar o orderStatistics abaixo.
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){ // esse metodo vai descobir a estatistica de ordem
		T out;
		if(!((array.length > 0) && (k >= 1) && (array != null))){
			out = null;
		} else {
			out = quick(array, k, 0, array.length-1);
		}
		return out;
		
	}

	private T quick(T[] array, int k, int leftIndex, int rightIndex) {
		if((leftIndex >= 0) && (array.length > 0) && (rightIndex <= array.length-1) && (leftIndex <= rightIndex)){
			int indexPivot = partition(array, leftIndex, rightIndex);

			if(k < indexPivot + 1){
				return quick(array, k, leftIndex, indexPivot-1);
			} else if (k > indexPivot + 1){
				return quick(array, k, indexPivot + 1, rightIndex);
			}
			return array[indexPivot];
		}	
		return array[k-1];
	}

	public int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex]; // pivot como sendo o primeiro elemento.
		int i = leftIndex;

		for (int j = leftIndex + 1; j < rightIndex; j++) {
			if(array[j].compareTo(pivot) <= 0){
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);
		return i;
	}
}
