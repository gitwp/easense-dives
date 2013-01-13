package org.easense.chapterthree;

/**
 * a simple implementation of Binary Search Tree
 * 
 * @author Easense
 * 
 * @param <K>
 *            the type of Key, must implement {@link Comparable}
 * @param <V>
 *            the type of Value
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

	/**
	 * Inner class node
	 * 
	 */
	class Node {
		K key;

		V value;

		Node left;

		Node right;

		int count;

		Node(K key, V value) {
			this(key, value, 1);
		}

		Node(K key, V value, int count) {
			this.key = key;
			this.value = value;
			this.count = count;
		}
	}

	private Node root;

	public void put(K key, V value) {
		this.root = putItem(root, key, value);
	}

	private Node putItem(Node node, K key, V value) {
		if (node == null) {
			return new Node(key, value);
		}

		int compare = node.key.compareTo(key);
		if (compare < 0) {
			node.right = putItem(node.right, key, value);
		} else if (compare > 0) {
			node.left = putItem(node.left, key, value);
		} else {
			node.key = key;
			node.value = value;
		}
		node.count = size(node.left) + size(node.right) + 1;
		return node;
	}

	public int getCount() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.count;
	}

	public boolean containsKey(K key) {
		return findNodeByKey(root, key) != null;
	}

	public V get(K key) {
		Node foundNode = findNodeByKey(root, key);
		return foundNode != null ? foundNode.value : null;
	}

	private Node findNodeByKey(Node root, K key) {
		if (root == null) {
			return null;
		}

		int compareRoot = root.key.compareTo(key);

		if (compareRoot == 0) {
			return root;
		} else if (compareRoot < 0) {
			return findNodeByKey(root.right, key);
		} else {
			return findNodeByKey(root.left, key);
		}
	}

	/**
	 * return number of keys less than give key
	 * 
	 * @param key
	 * @return
	 */
	public int rank(K key) {
		return rank(key, root);
	}

	/**
	 * Return number of keys less than node.key in the subtree rooted at node
	 * 
	 * @param key
	 * @param node
	 * @return
	 */
	private int rank(K key, Node node) {
		if (node == null) {
			return 0;
		}

		int compare = node.key.compareTo(key);
		if (compare == 0) {
			return size(node.left);
		} else if (compare > 0) {
			return rank(key, node.left);
		} else {
			return 1 + size(node.left) + rank(key, node.right);
		}

	}

	public void print() {
		printTreeInWideFirst(root);
		System.out.println();
	}

	private void printTreeInWideFirst(Node node) {
		if (node != null) {
			printTreeInWideFirst(node.left);
			printTreeInWideFirst(node.right);
			System.out.print(node.key + ":" + node.value + "\t");
		}
	}

	public static void main(String[] args) {

		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

		bst.put(5, "Hello");
		bst.put(2, "Jason");
		bst.put(6, "Siri");
		bst.put(1, "Cheliya");
		bst.put(4, "Xuanxuan");
		bst.put(5, "Jackson");
		bst.put(7, "Baobao");
		bst.put(3, "TestCommit");
		bst.put(10, "Jhon");

		bst.print();
		for (int i = 0; i <= 10; i++) {
			String value = bst.get(i);
			if (value != null) {
				System.out.println(i + " = " + bst.get(i));
			}
		}
	}
}
