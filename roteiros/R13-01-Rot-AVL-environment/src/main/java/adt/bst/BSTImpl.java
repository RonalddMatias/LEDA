package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(getRoot());	
	}

	protected int height(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result = 0;
			} else {
				result = 1 + Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getLeft()));
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> root) {
		BSTNode<T> result = null;

		if(root.isEmpty()){
			result = new BSTNode<>();
		} else if (root.getData().equals(element)){
			result = root;
		} else if(root.getData().compareTo(element) > 0){
			result = search(element, (BSTNode<T>) root.getLeft());
		} else {
			result = search(element, (BSTNode<T>) root.getRight());
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			insert(element, this.root);
		}
	}
	
	private void insert(T element, BSTNode<T> root) {
		if(root.isEmpty()){
			root.setData(element);
			root.setLeft(new BSTNode<>());
			root.setRight(new BSTNode<>());
			root.getLeft().setParent(root);
			root.getRight().setParent(root);
		} else {
			if(element.compareTo(root.getData()) < 0){
				insert(element, (BSTNode<T>) root.getLeft());
			} else if(element.compareTo(root.getData()) > 0){
				insert(element, (BSTNode<T>) root.getRight()); // eu chamo recursivamente pois em algum momento vai cair no caso que o mais a esqueda ou o mais a direita é vazio
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if(!isEmpty()){
			result = maximum(getRoot());
		}
		return result;
	}

	private BSTNode<T> maximum(BSTNode<T> root) {
		BSTNode<T> result = root;
		if(!root.isEmpty()){
			result = maximum((BSTNode<T>) root.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if(!isEmpty()){
			result = minimum(getRoot());
		}
		return result;
	}

	private BSTNode<T> minimum(BSTNode<T> root) {
		BSTNode<T> result = root;
		if(!root.isEmpty()){
			result = minimum((BSTNode<T>) root.getLeft());
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element); // validando elemento
		BSTNode<T> sucessor = null;
		if(!node.isEmpty()){
			if(!node.getRight().isEmpty()){ // se tiver elemento na diretira então pegue o minimo
				sucessor = minimum((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while(parent != null && node.equals(parent.getRight())){
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				sucessor = parent;
			}
		}
		return sucessor;
 	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> predecessor = null;
		if(!node.isEmpty()){
			if(!node.getLeft().isEmpty()){ // se tem elemento a esqueda, o maximo dele será o predecessor
				predecessor = maximum((BSTNode<T>) node.getLeft());
			} else { // se o elemento a esqueda é vazio, eu devo subir na minha arvore procurando o primeiro menor valor
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while(parent != null && node.equals(parent.getLeft())){
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				predecessor = parent;
			}
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		if(element != null){
			BSTNode<T> node = search(element);

			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		// 3 casos para a remoção: 
		// quando o no é um folha 
		// quando o no tem apenas um filho
		// quando
		if(!node.isEmpty() && node != null) {
			if(node.isLeaf()) {
				node.setData(null);
				
			}else if(temUmFilho(node)) { 
				
				if(node.getParent() != null) {
					if(node.getParent().getData().compareTo(node.getData()) > 0) {
						if(!node.getLeft().isEmpty()) {
							
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
							
						}else {
							
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}else {
						if(!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
					
				}else {
					if(node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
						root.setParent(null);
					}else {
						root = (BSTNode<T>) node.getLeft();
						root.setParent(null);
					}
				}
				
			}else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}

	private boolean temUmFilho(BSTNode<T> node) {
		boolean result = false;
		if((node.getLeft().isEmpty() && !node.getRight().isEmpty())|| (!node.getLeft().isEmpty() && node.getRight().isEmpty())){
			result = true;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		List<T> result = new ArrayList<>();
		preOrder(this.root, result);
		return (T[]) result.toArray(new Comparable[size()]);
		
	}

	private void preOrder(BSTNode<T> node, List<T> result) {
		if(!node.isEmpty()){
			result.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), result);
			preOrder((BSTNode<T>) node.getRight(), result);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		ArrayList<T> result = new ArrayList<>();
		order(this.root, result);	
		return (T[]) result.toArray(new Comparable[size()]);
		
	}

	private void order(BSTNode<T> node, List<T> result) {
		if(!node.isEmpty()){
			order((BSTNode<T>) node.getLeft(), result);
			result.add(node.getData());
			order((BSTNode<T>) node.getRight(), result);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		List<T> result = new ArrayList<>();
		postOrder(this.root, result);
		return (T[]) result.toArray(new Comparable[size()]);
	}

	

	private void postOrder(BSTNode<T> node, List<T> result) {
		if(!node.isEmpty()){
			postOrder((BSTNode<T>) node.getLeft(), result);
			postOrder((BSTNode<T>) node.getRight(), result);
			result.add(node.getData());
		}

	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
