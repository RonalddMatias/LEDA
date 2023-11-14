package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> nodeRotation = (BSTNode<T>) node.getRight();

		node.setRight(nodeRotation.getLeft());
		nodeRotation.setLeft(node);

		nodeRotation.setParent(node.getParent());
		node.setParent(nodeRotation);
		node.getRight().setParent(node);

		if (nodeRotation.getParent() != null) {
			if (nodeRotation.getParent().getRight().equals(node)) {
				nodeRotation.getParent().setRight(nodeRotation);
			} else {
				nodeRotation.getParent().setLeft(nodeRotation);
			}
		}
		return nodeRotation;
	}
	
	public static <T extends Comparable<T>> BSTNode<T> doubleLeftRotation(BSTNode<T> nodeRotation) {
		rightRotation((BSTNode<T>) nodeRotation.getRight());
		return leftRotation(nodeRotation);
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> nodeRotation = (BSTNode<T>) node.getLeft();

		node.setLeft(nodeRotation.getRight());
		nodeRotation.setRight(node);

		nodeRotation.setParent(node.getParent());
		node.setParent(nodeRotation);
		node.getLeft().setParent(node);

		if (nodeRotation.getParent() != null) {
			if (nodeRotation.getParent().getLeft().equals(node)) {
				nodeRotation.getParent().setLeft(nodeRotation);
			} else {
				nodeRotation.getParent().setRight(nodeRotation);

			}
		}
		return nodeRotation;
	}
	
	public static <T extends Comparable<T>> BSTNode<T> doubleRightRotation(BSTNode<T> nodeRotation) {
		leftRotation((BSTNode<T>) nodeRotation.getLeft());
		return rightRotation(nodeRotation);
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
