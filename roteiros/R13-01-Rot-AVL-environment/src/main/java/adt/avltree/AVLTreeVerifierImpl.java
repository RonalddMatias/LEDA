package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return (this.isBST() && this.isAVL());
	}

	private boolean isAVL() {
		return (this.avlTree.isEmpty() || this.isAVLRecursivo(this.avlTree.getRoot()));
	}

	private boolean isAVLRecursivo (BSTNode<T> node) {
		boolean resposta = true;
		if (!node.isEmpty()){
			if (Math.abs(this.avlTree.calculateBalance(node)) <= 1) {
				resposta = this.isAVLRecursivo((BSTNode<T>) node.getLeft()) && this.isAVLRecursivo((BSTNode<T>) node.getRight());
				
			}else{
				resposta = false;
			}

		}
		return resposta;
	}
}
