package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	// floor é o numero abaixo mais proximo 
	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;
		if(array != null && array.length > 0){
			for(Integer num : array){
				insert(num);
			}
			result = floorRecursivo(getRoot(), numero, result);
		}
		return result;
	}

	private Integer floorRecursivo(BSTNode<Integer> node, double numero, Integer result) {
		if(!node.isEmpty()){
			if(numero > node.getData()){
				result = floorRecursivo((BSTNode<Integer>) node.getRight(), numero, node.getData()); // procurar no lado direito
			} else if (numero < node.getData()){
				result = floorRecursivo((BSTNode<Integer>) node.getLeft(), numero, result); // procurar no lado esquerdo
			} else {
				result = node.getData(); // caso eles sejam iguais
			}
		}
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = null;
		if(array != null && array.length > 0){
			for(Integer num: array){
				insert(num);
			}
			result = ceilRecursivo(getRoot(), numero, result);
		}
		return result;
		
	}

	private Integer ceilRecursivo(BSTNode<Integer> node, double numero, Integer result) {
		if(!node.isEmpty()){
			if(numero > node.getData()){
				result = ceilRecursivo((BSTNode<Integer>) node.getRight(), numero, result);
			} else if (numero < node.getData()){
				result = ceilRecursivo((BSTNode<Integer>) node.getLeft(), numero, node.getData());
			} else {
				result = node.getData(); 
			}
		}
		return result;
	}

}
