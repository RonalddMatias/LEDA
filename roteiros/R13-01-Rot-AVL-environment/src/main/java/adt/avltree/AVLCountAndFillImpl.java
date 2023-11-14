package adt.avltree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length > 0) {
			List<T> list = new ArrayList<>();
			for (T e : preOrder()){
				list.add(e);
			} 
			
			for (T e : array){
				if (!list.contains(e)) {
					list.add(e);
				}
			}
			this.root = new BSTNode<>();
			Collections.sort(list);
			Map<Integer, List<T>> mapa = new HashMap<>();
			fillRecursivo(mapa, 0, list.size() - 1, list, 0);
			int altura = 0;
			while (mapa.containsKey(altura)) {

				for (T e : mapa.get(altura)){
					insert(e);
				}
				
				altura+=1;
			}
  		}
	}
	
	private void fillRecursivo(Map<Integer, List<T>> mapa, int left, int right, List<T> array, int altura) {
		if (left <= right) {
			int meio = (left + right) / 2;
			T elemento = array.get(meio);
			if (!mapa.containsKey(altura)){
				mapa.put(altura, new ArrayList<T>());
			}
			mapa.get(altura).add(elemento);
			fillRecursivo(mapa, left, meio - 1, array, altura + 1);
			fillRecursivo(mapa, meio + 1, right, array, altura + 1);
		}
	}
	
	@Override
	protected void rebalance(BSTNode<T> node) {
		int balanceamento = calculateBalance(node);
		if (balanceamento > 1){
			rebalancearEsq(node);
		} else if (balanceamento < -1){
			rebalancearDir(node);
		}
	}
	
	private void rebalancearEsq(BSTNode<T> node) {
		BSTNode<T> aux;
		if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
			aux = Util.rightRotation(node);
			LLcounter++;
			
		} else {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			aux = Util.rightRotation(node);
			LRcounter++;
		}

		if (aux.getParent() == null){
			this.root = aux;
		}
	}
	
	private void rebalancearDir(BSTNode<T> node) {
		BSTNode<T> aux;
		if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
			aux = Util.leftRotation(node);
			RRcounter++;
			
		} else {	
			Util.rightRotation((BSTNode<T>) node.getRight());
			aux = Util.leftRotation(node);
			RLcounter++;
		}

		if (aux.getParent() == null){
			this.root = aux;
		}
	}
}