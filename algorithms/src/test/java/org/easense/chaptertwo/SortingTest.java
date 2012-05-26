package org.easense.chaptertwo;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class SortingTest {
	
	// define the array's size
	private final int PROBLEM_SCALE = 10000;

	@Test
//	@Ignore
	public void selectionSort() {
		sort(SortType.SELECTION);
	}

	@Test
//	@Ignore
	public void insertionSort() {
		sort(SortType.INSERTION);
	}
	
	@Test
	public void bubbleSort() {
		sort(SortType.BUBBLE);
	}
	
	@Test
	public void shellSort() {
		sort(SortType.SHELL);
	}

	private void sort(SortType sortType) {
		Integer[] array = generateArray(PROBLEM_SCALE);
		sortType.sort(array);
		
		Assert.assertTrue(isSorted(array));
	}

	private Integer[] generateArray(int length) {
		Random rand = new Random();
		Integer[] array = new Integer[length];

		for (int i = 0; i < length; i++) {
			array[i] = rand.nextInt(length * 4);
		}
		return array;
	}
	
	private <T extends Comparable<T>> boolean isSorted(T[] array) {
		if (array == null || array.length <= 2) {
			return true;
		}
		
		boolean lastCompare = array[0].compareTo(array[1]) <= 0;
		
		for (int i = 2; i < array.length; i++) {
			if (array[i - 1].compareTo(array[i]) > 0 == lastCompare) {
				return false;
			}
		}
		
		return true;
	}
}
