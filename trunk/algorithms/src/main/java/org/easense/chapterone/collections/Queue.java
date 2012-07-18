package org.easense.chapterone.collections;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

	/**
	 * Inner Node class, used as Linked Nodes
	 * 
	 * @author Easense
	 */
	private class Node {
		Item item;
		Node next;

		Node(Item item) {
			this.item = item;
		}
	}

	private Node first;

	private Node last;

	private int count;

	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * add a new item at the last position of the queue
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		Node newNode = new Node(item);

		if (isEmpty()) {
			first = last = newNode;
		} else {
			last = last.next = newNode;
		}
		count++;
	}

	/**
	 * return the first Item in the queue and decrease the queue's length by one
	 * 
	 * @return
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("the queue is empty!");
		}

		Item itemToPop = first.item;
		first = first.next;

		// handling the last one dequeue in the queue to avoid memory leak
		if (first == null) {
			last = null;
		}
		count--;

		return itemToPop;

	}

	public int size() {
		return this.count;
	}

	public void reverse() {
		// skip to do anything if the queue is empty so far
		if (isEmpty()) {
			return;
		}

		Node first = this.first;
		Node reverse = null;

		while (first != null) {
			// store the node next to first one
			Node second = first.next;
			// point first.next to current leading node of reversed
			first.next = reverse;
			// set head of reversed queue
			reverse = first;
			// move to next node in the original queue
			first = second;
		}

		this.first = reverse;

		// the recursive implementation
		// this.first = reverse(this.first);
	}

	/**
	 * reverse the rest of queue after the given first node
	 * 
	 * @param first
	 * @return
	 */
	@SuppressWarnings("unused")
	private Node reverse(Node first) {
		if (first == null || first.next == null) {
			return first;
		}
		Node second = first.next;
		Node rest = reverse(second);
		// reverse the direction of the first and second nodes
		second.next = first;
		first.next = null;
		return rest;
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			Node currentNode = first;

			public boolean hasNext() {
				return currentNode != null;
			}

			public Item next() {
				Item nextItem = currentNode.item;
				currentNode = currentNode.next;
				return nextItem;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
