package orderStatistic;

import java.util.PriorityQueue;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap restricoes:
	 * - nenhuma copia ou estrutura array auxiliar serah permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatistica de ordem procurada nao exista no array o metodo deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	@Override
	public T getOrderStatistics(T[] array, int k) {
		T output = null;
		if(k >= 1 && k <= array.length){
			PriorityQueue<T> heap = new PriorityQueue<T>();

			for (T obj : array) { // adicionar o conteudo que esta no array e passar para a minha heap
				heap.add(obj);
			}
			for (int i = k; i > 0; k--) {
				output = heap.poll();
			}
		}
		return output;
	}

	
	
}
