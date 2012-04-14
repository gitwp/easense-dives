package org.easense;

public class SentenceReverser {

	public static String reverseWords(String orginalStr) {
		if (orginalStr == null || orginalStr.trim().equals("")) {
			return orginalStr;
		}

		char[] chars = orginalStr.toCharArray();

		// reverse whole sentence
		reversWord(chars, 0, chars.length - 1);

		int wordStart = 0;
		boolean isSeperator = true;

		for (int idx = 0; idx < chars.length; idx++) {
			if (isSeperator && chars[idx] != ' ') {
				wordStart = idx;
				isSeperator = false;
			} else if (!isSeperator) {
				if (chars[idx] == ' ') {
					reversWord(chars, wordStart, idx - 1);
					isSeperator = true;
				} else if (idx == chars.length - 1) {
					reversWord(chars, wordStart, idx);
				}
			}
		}

		return new String(chars);
	}

	public static void reversWord(char[] chars, int startIdx, int endIdx) {
		if (startIdx >= endIdx || chars == null || chars.length <= 1) {
			return;
		}
		while (startIdx < endIdx) {
			chars[startIdx] ^= chars[endIdx];
			chars[endIdx] ^= chars[startIdx];
			chars[startIdx++] ^= chars[endIdx--];
		}
	}

	public static void main(String[] args) {
		String orginalStr = " I am a good and clever student ";
		System.out.println(reverseWords(orginalStr));
	}
}
