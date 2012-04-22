package org.easense;

import java.util.Arrays;

import org.junit.Test;

public class RandomTest {

	@Test
	public void testShuffle() {
		System.out.println(Arrays.toString(RandomUtil.multipleShuffle(new Integer[] { 1, 3, 5, 7 }, 3)));
		System.out.println(Arrays.toString(RandomUtil.multipleShuffle(new String[] { "Foo", "Bar", "test" }, 3)));
		System.out.println(Arrays.toString(RandomUtil.shuffle(2, 1, 9, 4, 5, 8, 10)));
	}
}
