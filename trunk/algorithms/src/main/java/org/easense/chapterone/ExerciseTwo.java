package org.easense.chapterone;

public class ExerciseTwo {

	/**
	 * circular shift, TAVCAG is twice shifting of AGTAVC
	 * 
	 * @param str
	 * @param times
	 * @return
	 */
	public static String circularShift(String str, int times) {
		int len = str.length();
		char[] result = new char[len];
		char[] strArray = str.toCharArray();
		for (int i = 0; i < len; i++) {
			result[(i + len - times) % len] = strArray[i];
		}
		return new String(result);
	}

	/**
	 * Check whether two given strings are circular rotation
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isCircularRotation(String s, String t) {
		if (s != null && t != null && s.length() == t.length()) {
			if (s.equals(t)) {
				return true;
			}

			// use classic method to implement
			for (int shift = 1; shift < s.length(); shift++) {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt((i + s.length() - shift) % s.length()) != t.charAt(i)) {
						break;
					}

					if (i == s.length() - 1) {
						return true;
					}
				}

			}

			// another creative implementation
			// return (s + s).contains(t);
		}

		return false;
	}
}
