package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer output = null;
		if (array != null){
			for (Integer number : array) {
				insert(number);
			}
			output = findFloor(numero, null);
		}
		return output;
	}

	private Integer findFloor(double numero, Integer floor) {
		Integer root =  extractRootElement(); // elemento
		if(root != null){
			boolean verifica = floor == null || root >= floor; // essa segunda verificação segura para quando nos acharmos o primeiro floor
			if(verifica && numero >= root){
				floor = findFloor(numero, root);
			} else {
				floor = findFloor(numero, floor);
			}
		}
		return floor; // so vai parar quando o node for null
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer output = null;
		if(array != null){
			for (Integer number: array) {
				insert(number);
			}
			output = findCeil(numero, null);
		}
		return output;
	}

	private Integer findCeil(double numero, Integer ceil) {
		Integer root = extractRootElement();
		if(root != null){
			boolean verifica = ceil == null || root <= numero;
			if(verifica && numero <= root){
				ceil = findCeil(numero, root);
			} else {
				ceil = findCeil(numero, ceil);
			}
		}
		return ceil;
	}

}
