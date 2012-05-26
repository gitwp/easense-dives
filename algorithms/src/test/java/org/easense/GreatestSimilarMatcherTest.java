package org.easense;

import org.easense.strproc.GreatestSimilarMatcher;
import org.junit.Assert;
import org.junit.Test;

public class GreatestSimilarMatcherTest {

	@Test
	public void testMatch() {
		test(GreatestSimilarMatcher.DEFAULT);
	}

	@Test
	public void anotherTest() {
		test(GreatestSimilarMatcher.INDEXOF_IMPL);
	}

	private void test(GreatestSimilarMatcher matcher) {
		for (int i = 0; i < 200; i++) {
			String matchedStr = matcher
					.findGreatestMatch(
							"xdgeaabcdlihixdgeaqwrtuiyurytotohhslghnsegdcgeabcdlihixdgeaabcdlihixdgeaabcdlihixdgeaabcdlihixdgeaabcdlwegsihixdgeaabcdlihi",
							"seegeerhrhyiiaabcdrtrllwewrgerswrwertuiyurytotohhslghnsegfwwdgwesgwegeswgwwegswwssessgesd");
			Assert.assertTrue("rtuiyurytotohhslghnseg".equals(matchedStr));
		}
	}
}
