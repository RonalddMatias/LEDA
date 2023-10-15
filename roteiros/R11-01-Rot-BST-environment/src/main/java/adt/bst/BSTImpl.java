package adt.bst;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Element;

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



	private int height(BSTNode<T> node) {
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

	private BSTNode<T> search(T element, BSTNode<T> node){
		BSTNode<T> result = null;

		if(node.isEmpty()){
			result = new BSTNode<T>();
		} else if (node.getData().equals(element)){
			result = node;
		} else if (node.getData().compareTo(element) > 0){
			result = search(element, (BSTNode<T>) node.getLeft());
		} else {
			result = search(element, (BSTNode<T>) node.getRight());
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
		BSTNode<T> resposta = null;
		if(!isEmpty()) {
			resposta = maximum(getRoot());
		}
		return resposta;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> aux = node;
		if(!node.getRight().isEmpty()) {
			aux = maximum((BSTNode<T>) node.getRight());
		}
		return aux;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> resposta = null;
		if(!isEmpty()) {
			resposta = minimum(root);
		}
		return resposta;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> aux = node;
		if(!node.getLeft().isEmpty()) {
			aux = minimum((BSTNode<T>) node.getLeft());
		}
		return aux;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element); // vendo se o meu elemento é valido
		BSTNode<T> sucessor = null;
		if(!node.isEmpty()){ // empty verifica se é null
			if(!node.getRight().isEmpty()){
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

	// pre Ordem: NO, ESQUERDA, DIREITA
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
