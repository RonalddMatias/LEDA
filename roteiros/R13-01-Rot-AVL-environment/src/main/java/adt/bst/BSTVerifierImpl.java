package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return getBSt().isEmpty() || isBST(getBSt().getRoot());
	}

	private boolean isBST(BSTNode<T> node) {
		boolean isBST = true;

		if(!node.isEmpty()){
			if (verificaEsquerda(node) && verificaDireita(node)) {
				isBST = isBST((BSTNode<T>) node.getLeft()) && isBST((BSTNode<T>) node.getRight());
			} else {
				isBST = false;
			}
		}
		return isBST();
		
	}

	private boolean verificaDireita(BSTNode<T> node) {
		return verificaDireita(node.getRight(), node);
	}

	private boolean verificaDireita(BTNode<T> right, BSTNode<T> node) {
		boolean verifica = true;
		if (!right.isEmpty()) {
			if (right.getData().compareTo(node.getData()) > 0) {
				verifica = verificaDireita(right.getLeft(), node) && verificaDireita(right.getRight(), node);
			} else {
				verifica = false;
			}
		}
		return verifica;
	}

	private boolean verificaEsquerda(BSTNode<T> node) {
		return verificaEsquerda(node.getLeft(), node);
	}

	private boolean verificaEsquerda(BTNode<T> left, BSTNode<T> node) {
		boolean verifica = true;
		if (!left.isEmpty()) {
			if (left.getData().compareTo(node.getData()) < 0) {
				verifica = verificaEsquerda(left.getLeft(), node) && verificaDireita(left.getRight(), node);
			} else {
				verifica = false;
			}
		}
		return verifica;
	}
	
}
