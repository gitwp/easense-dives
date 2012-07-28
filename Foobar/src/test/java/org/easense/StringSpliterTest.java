package org.easense;

import java.util.Arrays;
import java.util.StringTokenizer;

import junit.framework.Assert;

import org.junit.Test;

public class StringSpliterTest {
	
	private String testString = "This, 		is	a;	 test";
	
	@Test
	public void test() {
		String resultByTokenizer = splitUsingStringTokenizer(testString);
		String resultByRegex = splitUsingRegex(testString);
		
		System.out.println("splitUsingStringTokenizer: " + resultByTokenizer);
		System.out.println("splitUsingRegex: " + resultByRegex);
		
		Assert.assertTrue(resultByTokenizer.equals(resultByRegex));
	}
	
	private String splitUsingStringTokenizer(String str) {
		StringBuilder result = new StringBuilder("[");
		StringTokenizer tokenizer = new StringTokenizer(str, ",; \t\n");
		
		while (tokenizer.hasMoreTokens()) {
			result.append(tokenizer.nextToken());
			if (tokenizer.hasMoreTokens()) {
				result.append(", ");
			}
		}
		
		result.append("]");
		
		return result.toString();
	}
	
	private String splitUsingRegex(String str) {
		return Arrays.toString(str.split("[,; \t\n]+"));
	}
}
