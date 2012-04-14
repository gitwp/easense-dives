package org.easense;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class RandomUtil {
	public static <T> T[] multipleShuffle(final T[] source, int factor) {
		if (factor < 1) {
			throw new IllegalArgumentException("factor mustn't be less than 1");
		}

		int genLen = source.length * factor;

		@SuppressWarnings("unchecked")
		T[] generated = (T[])Array.newInstance(source.getClass().getComponentType(), genLen);
		boolean[] visited = new boolean[genLen];

		Random random = new Random();

		for (T nextVal : source) {
			int nextId;
			for (int genTimes = 0; genTimes < factor; genTimes++) {
				do {
					nextId = random.nextInt(genLen);
				} while (visited[nextId]);
				generated[nextId] = nextVal;
				visited[nextId] = true;
			}
		}

		return generated;
	}
	
	public static <T> T[] shuffle(T... elements) {
		if (elements.length <= 1) {
			return elements;
		}
		
		return multipleShuffle(elements, 1);
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(RandomUtil.multipleShuffle(new Integer[] { 1, 3, 5, 7 }, 3)));
		System.out.println(Arrays.toString(RandomUtil.multipleShuffle(new String[] { "Foo", "Bar", "test" }, 3)));
		System.out.println(Arrays.toString(RandomUtil.shuffle(2, 1, 9, 4, 5, 8, 10)));
	}
}
