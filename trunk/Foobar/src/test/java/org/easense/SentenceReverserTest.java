package org.easense;

import org.junit.Test;

public class SentenceReverserTest {

	@Test
	public void testReverse() {
		String orginalStr = " I am a good and clever student ";
		System.out.println(SentenceReverser.reverseSentence(orginalStr));
	}
}
