package adt.bst;

import javax.swing.text.html.parser.Element;

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
		return height(this.getRoot());
	}



	private int height(BSTNode<T> node) {
		int result;
		if(root == null){
			result = -1;
		} else {
		  result = 1 + Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<>();
		if(!isEmpty() && element != null){ // quando eu for pesquisar eu tenho que ver se a minha bst está vazia.
			result = search(element, this.root);
		}
		return result;
	}

	private BSTNode<T> search(T element, BSTNode<T> node){
		BSTNode<T> result = node;

		if(!node.getData().equals(element) || node != null){ // condição de parada
			if(element.compareTo(node.getData()) < 0){
				result = search(element, (BSTNode<T>) node.getLeft());
			} else {
				result = search(element, (BSTNode<T>) node.getRight());
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if(node.isEmpty()){
			node.setData(element); // setando o valor
			node.setLeft(new BSTNode<>()); // criando um node vazio na esquerda.
			node.setRight(new BSTNode<>()); // criando um node vazio na direira.
			node.getLeft().setParent(node); // setando o parente do node a esquerda criado como sendo o node.
			node.getRight().setParent(node); // setando o parente do node a direita criado como sendo o node.
		} else {
			if(element.compareTo(node.getData()) < 0){
				insert(element, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0){
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> root) {
		BSTNode<T> aux = root;

		if(!(aux.getRight() == null)){
			aux =  maximum((BSTNode<T>) root.getRight());
		}

		return aux;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> root2) {
		BSTNode<T> aux = root;

		if(!(aux.getLeft() == null)){
			aux =  maximum((BSTNode<T>) root.getRight());
		}

		return aux;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
