package org.easense;

import org.easense.chapterone.collections.DoubleLinkedList;
import org.junit.Test;

public class DoubleLinkedListTest {
	
	@Test
	public void normalTest() {
		DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<String>();
		doubleLinkedList.append("hello");
		doubleLinkedList.append("world");
		
		doubleLinkedList.prepend("Jason says");
		
		doubleLinkedList.insertBefore(3, "to");
		
		doubleLinkedList.insertAfter(3, "my");
		
		for (String str : doubleLinkedList) {
			System.out.println(str);
		}
	}
}
