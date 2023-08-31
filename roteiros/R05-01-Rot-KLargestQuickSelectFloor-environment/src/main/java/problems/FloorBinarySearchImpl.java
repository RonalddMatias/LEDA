package problems;

import java.util.Arrays;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if(array.length > 0 && x != null){

			QuickSort(array, 0, array.length-1);
			System.out.println(Arrays.toString(array));

			return binarySearch(array, x, null,0, array.length-1);
		}
		return null;
	}


	private int binarySearch(Integer[] array, Integer x, Integer floor, int leftIndex, int rightIndex) {
		if((leftIndex >= 0)  && (rightIndex <= array.length) && (leftIndex != rightIndex) && (array.length > 0)){
			if (leftIndex < rightIndex){
				System.out.println(Arrays.toString(array));

				int middle = (leftIndex + rightIndex) / 2;

				// verificando se o meio é igual ao numero procurado
				if(array[middle] == x){
					return array[middle];
				} else if (array[middle].compareTo(x) < 0){
					int test = array[middle];
					return binarySearch(array, x,array[middle], middle + 1, rightIndex); // olhando para a direita do array
				} else {
					return binarySearch(array, x, floor, leftIndex, middle - 1); // olhando para a esquerda do array
				}
		}
	}	
	return floor;
}


	public void QuickSort(Integer[] array, int leftIndex, int rightIndex) {
		if((leftIndex >= 0)  && (rightIndex <= array.length) && (leftIndex != rightIndex) && (array.length > 0)){
			if(leftIndex < rightIndex){
			int index_pivot = partition(array, leftIndex, rightIndex);
			QuickSort(array, leftIndex, index_pivot-1);
			QuickSort(array, index_pivot+1, rightIndex);
			}
		}
	}


	// Algoritmo escolhido para ordenar o array para depois aplicar a busca binária.
	public int partition(Integer[] array, int leftIndex, int rightIndex){
		int pivot = array[leftIndex]; // escolhendo o pivot com sendo sempre a primeira posiçao
		int i = leftIndex;

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
		Integer[] num = new Integer[]{4,15,25,30,38};
		System.out.println(floorBinarySearchImpl.floor(num, 3));
	}

}
