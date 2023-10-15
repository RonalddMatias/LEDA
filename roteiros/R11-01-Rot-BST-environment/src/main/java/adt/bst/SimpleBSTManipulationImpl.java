package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}
	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean equals = false;
		if (node1.isEmpty() && node2.isEmpty()) {
			equals = true;
		} else if (!node1.isEmpty() & !node2.isEmpty()) {
			if (node1.equals(node2)) {
				equals = equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
						&& equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			}
		}

		return equals;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean similar = false;
		if (node1.isEmpty() && node2.isEmpty()) {
			similar = true;
		} else if (!node1.isEmpty() && !node2.isEmpty()) {
			similar = isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return similar;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T order = null;
		int size = tree.size();
		if (k >= 1 && k <= size) {
			if (k == 1) {
				order = tree.minimum().getData();
			} else if (k == size) {
				order = tree.maximum().getData();
			} else {
				order = orderStatistic(tree, tree.minimum(), k);
			}
		}
		return order;
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> node, int k) {
		T order = null;
		if (k == 1) {
			order = node.getData();
		} else {
			order = orderStatistic(tree, tree.sucessor(node.getData()), --k);
		}
		return order;
	}
}
