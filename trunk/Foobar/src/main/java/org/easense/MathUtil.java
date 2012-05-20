package org.easense;

public class MathUtil {

	/**
	 * a recursive implementation to find a max one from a group of Comparable
	 * 
	 * @param numbers
	 * @return
	 */
	public static <T extends Comparable<T>> T findMax(T... numbers) {
		return findMax(numbers, 0, numbers.length);
	}

	/**
	 * the real implementation
	 * 
	 * @param numbers
	 * @param startIdx
	 * @param length
	 * @return
	 */
	private static <T extends Comparable<T>> T findMax(T[] numbers, int startIdx, int length) {
		if (length < 1) {
			throw new IllegalArgumentException("the argument length mustn't be less than 1");
		}
		if (length == 1) {
			return numbers[startIdx];
		}
		T maxOfRest = findMax(numbers, startIdx + 1, length - 1);
		return numbers[startIdx].compareTo(maxOfRest) > 0 ? numbers[startIdx] : maxOfRest;
	}

}
