package org.easense.chaptertwo;

import java.util.Arrays;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class SortingTest {

	@Test
	@Ignore
	public void selectionSort() {
		sort(SortType.SELECTION);
	}

	@Test
	@Ignore
	public void insertionSort() {
		sort(SortType.INSERTION);
	}
	
	@Test
	public void bubbleSort() {
		sort(SortType.BUBBLE);
	}

	private void sort(SortType sortType) {
		Integer[] array = generateArray(20);
		System.out.println("Before: " + Arrays.toString(array));
		sortType.sort(array);
		System.out.println("After " + sortType.toString() + " sort: " + Arrays.toString(array) + "\n");
	}

	private Integer[] generateArray(int length) {
		Random rand = new Random();
		Integer[] array = new Integer[length];

		for (int i = 0; i < length; i++) {
			array[i] = rand.nextInt(length * 4);
		}
		return array;
	}
}
