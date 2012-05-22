package org.easense.chapterone.collections;

import java.util.Iterator;

public class DoubleLinkedList<Item> implements Iterable<Item> {

	/**
	 * Inner Node class, used as Linked Nodes
	 * 
	 * @author Easense
	 * 
	 * @param <E>
	 */
	private class Node<E> {
		E item;
		Node<E> next;
		Node<E> previous;

		Node(E item) {
			this.item = item;
		}
	}

	private Node<Item> head;

	private Node<Item> tail;

	private int count;

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return this.count;
	}

	public void prepend(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = head.previous = newNode;
		}
		count++;
	}

	public void append(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.previous = tail;
			tail = tail.next = newNode;
		}
		count++;
	}

	public Item popHead() {
		if (isEmpty()) {
			throw new RuntimeException("current list is empty!");
		}
		Item item = head.item;
		head = head.next;
		head.previous = null;
		return item;
	}

	public Item popTail() {
		if (isEmpty()) {
			throw new RuntimeException("current list is empty!");
		}
		Item item = tail.item;
		tail = head.previous;
		tail.next = null;
		return item;
	}

	/**
	 * find node in the list by index
	 * 
	 * @param index
	 *            started from 1
	 * @return
	 */
	private Node<Item> findNodeIn(int index) {
		if (index < 1 || index > count) {
			throw new IllegalArgumentException("Index out of bounds: " + index + ", allowed is [1, " + count + "]");
		}

		if (index > count / 2) {
			// searching from tail end
			Node<Item> current = tail;
			for (int i = 1; i <= count - index; i++) {
				current = current.previous;
			}
			return current;
		} else {
			// searching from head end
			Node<Item> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			return current;
		}
	}

	/**
	 * insert a node before the given index position,<br/>
	 * if the index is one, this call will be equivalent to <code>prepend(item)</code>
	 * @param index started from 1
	 * @param item
	 */
	public void insertBefore(int index, Item item) {
		if (index == 1) {
			prepend(item);
		} else {
			Node<Item> nextNode = findNodeIn(index);
			insertBetween(nextNode.previous, nextNode, item);
		}
		
	}
	
	/**
	 * insert a node after the given index position,<br/>
	 * if the index is equal to current size of the list, this call will be equivalent to <code>append(item)</code>
	 * @param index started from 1
	 * @param item
	 */
	public void insertAfter(int index, Item item) {
		if (index == count) {
			append(item);
		} else {
			Node<Item> previousNode = findNodeIn(index);
			insertBetween(previousNode, previousNode.next, item);
		}
	}
	
	private void insertBetween(Node<Item> previous, Node<Item> next, Item item) {
		Node<Item> newNode = new Node<Item>(item);
		previous.next = next.previous = newNode;
		newNode.previous = previous;
		newNode.next = next;
		count++;
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Node<Item> current = head;
			
			public boolean hasNext() {
				return current != null;
			}

			public Item next() {
				if (hasNext() == false) {
					throw new RuntimeException("There is no more element left");
				}
				Item item = current.item;
				current = current.next;
				return item;
			}

			public void remove() {
				throw new UnsupportedOperationException("Under construction...");				
			}
			
		};
	}
}
