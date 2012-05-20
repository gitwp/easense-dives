package org.easense.chapterone.collections;

import java.util.Iterator;

import edu.princeton.StdIn;

public class Stack<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
	}

	private Node top;

	private int count;

	public boolean isEmpty() {
		return top == null;
	}

	public void push(Item item) {
		Node oldTop = top;

		Node newNode = new Node();
		newNode.item = item;

		top = newNode;
		newNode.next = oldTop;
		count++;

	}

	public Item pop() {
		if (isEmpty()) {
			return null;
		}
		count--;
		Item popItem = top.item;
		top = top.next;
		return popItem;
	}

	public int size() {
		return this.count;
	}

	public static void main(String[] args) {
		Stack<Integer> intStack;
		while (StdIn.isEmpty() == false) {
			String input = StdIn.readLine();
			intStack = new Stack<Integer>();
			StringBuilder sb = new StringBuilder();
			for (String str : input.split("\\s+")) {
				if ("-".equals(str)) {
					sb.append(intStack.pop()).append(" ");
				} else {
					intStack.push(Integer.valueOf(str));
				}
			}
			System.out.println("output: " + sb);
		}
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			Node current = top;

			public boolean hasNext() {
				return current != null;
			}

			public Item next() {
				Item popItem = current.item;
				current = current.next;
				return popItem;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
