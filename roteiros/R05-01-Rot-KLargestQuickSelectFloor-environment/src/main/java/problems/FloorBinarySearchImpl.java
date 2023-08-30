package problems;

import java.util.Arrays;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		QuickSort(array, 0, array.length-1);
		// TODO implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	public void QuickSort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			int index_pivot = partition(array, leftIndex, rightIndex);
			QuickSort(array, leftIndex, index_pivot-1);
			QuickSort(array, index_pivot+1, rightIndex);
		}
	}


	// Algoritmo escolhido para ordenar o array para depois aplicar a busca binária.
	public int partition(Integer[] array, int leftIndex, int rightIndex){
		int pivot = array[leftIndex]; // escolhendo o pivot com sendo sempre a primeira posiçao
		int i = 0;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if(array[j].compareTo(pivot) < 0){
				i++;
				Util.swap(array, i, j);
			}
		}

		Util.swap(array, leftIndex, i);
		return i;
	}

	public static void main(String[] args) {
		FloorBinarySearchImpl floorBinarySearchImpl = new FloorBinarySearchImpl();
		Integer[] num = new Integer[]{4, 10, 2, 0, -21, 12};
		floorBinarySearchImpl.floor(num, 2);
		System.out.println(Arrays.toString(num));
	}

}
