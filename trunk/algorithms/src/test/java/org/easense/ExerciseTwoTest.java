package org.easense;

import junit.framework.Assert;

import org.easense.chapterone.ExerciseTwo;
import org.junit.Test;

public class ExerciseTwoTest {
	
	@Test
	public void testCircularRotation() {
		String a = "abcdeft";
		String b = "cdeftab";
		String c = "tabcdef";
		String e = "sgged";
		
		Assert.assertTrue(ExerciseTwo.isCircularRotation(a, b));
		Assert.assertTrue(ExerciseTwo.isCircularRotation(a, c));
		Assert.assertTrue(ExerciseTwo.isCircularRotation(b, c));
		Assert.assertFalse(ExerciseTwo.isCircularRotation(c, e));
	}
}
