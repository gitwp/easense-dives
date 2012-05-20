package org.easense;

import org.easense.chapterone.collections.Queue;
import org.junit.Ignore;
import org.junit.Test;

public class QueueTest {
	
	@Test
	@Ignore
	public void testOne() {
		Queue<String> queue = new Queue<String>();
		queue.enqueue("Hello");
		queue.enqueue("My");
		queue.enqueue("Friend");
		
		for (String str : queue) {
			System.out.println(str);
		}
		
		while (queue.isEmpty() == false) {
			System.out.println(queue.dequeue());
		}
	}
	
	@Test
	public void testReverse() {
		Queue<String> queue = new Queue<String>();
		queue.enqueue("Hello");
		queue.enqueue("My");
		queue.enqueue("Friend");
		
		System.out.print("Before reversed: ");
		for (String str : queue) {
			System.out.print(str + " ");
		}
		
		queue.reverse();
		
		System.out.print("\nAfter reversed: ");
		for (String str : queue) {
			System.out.print(str + " ");
		}
		
		System.out.println();
	}
}
