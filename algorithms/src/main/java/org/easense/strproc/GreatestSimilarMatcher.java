package org.easense.strproc;

class DefaultMatcher implements GreatestSimilarMatchable {

	public String findGreatestMatch(String shorterOne, String longerOne) {
		final int shorterLen = shorterOne.length();
		final int longerLen = longerOne.length();

		int maxMatchStarts = 0;
		int maxMatchedLen = 0;

		for (int i = 0; i < shorterLen;) {

			int currentMatchedLen = 0;
			int seekIdx = 0;

			// need to loop all the letters in the longer one
			while (seekIdx < longerLen) {
				currentMatchedLen = 0;

				// finder the next first index of the letter in the longer one
				// which is same as the letter in i position
				// in the shorter one
				while (seekIdx < longerLen && shorterOne.charAt(i) != longerOne.charAt(seekIdx)) {
					seekIdx++;
				}

				// begin to match the afterward letters in the longer one
				while (i + currentMatchedLen < shorterLen && seekIdx < longerLen
						&& shorterOne.charAt(i + currentMatchedLen) == longerOne.charAt(seekIdx)) {
					seekIdx++;
					currentMatchedLen++;
				}

				// if the found matched string's length is larger than the stored 
				// max matched length so far, use the current max length to replace
				// it and set the start index accordingly,
				// then move the search index forward maxMatchedLen chars and continue
				// to looking for maybe larger matched length in the longer one
				if (currentMatchedLen > maxMatchedLen) {
					maxMatchedLen = currentMatchedLen;
					seekIdx += currentMatchedLen;
					maxMatchStarts = i;
				}
			}

			// if current compare found no match, move forward one char
			// otherwise move currentMatchedLen chars
			i += (currentMatchedLen == 0 ? 1 : currentMatchedLen);
		}

		return shorterOne.substring(maxMatchStarts, maxMatchedLen + maxMatchStarts);
	}

}

class IndexOfImpl implements GreatestSimilarMatchable {

	public String findGreatestMatch(String shorterOne, String longerOne) {
		int shorterLen = shorterOne.length();
		int longerLen = longerOne.length();
		
		for (int i = shorterLen; i > 0; i--) {
			for (int j = 0; j < (longerLen - i); j++) {
				String subStr = longerOne.substring(j, j + i);
				if (shorterOne.indexOf(subStr) != -1) {
					return subStr;
				}
			}
		}
		return null;
	}
	
}

public enum GreatestSimilarMatcher implements GreatestSimilarMatchable {

	/**
	 * Default implementation
	 */
	DEFAULT(new DefaultMatcher()),
	
	/**
	 * Another implementation using String's API indexOf<br/>
	 * This will definitely use more time and spaces 
	 */
	INDEXOF_IMPL(new IndexOfImpl());

	private GreatestSimilarMatcher(GreatestSimilarMatchable matcherAlgorithm) {
		this.matcherAlgorithm = matcherAlgorithm;
	}

	private GreatestSimilarMatchable matcherAlgorithm;

	public String findGreatestMatch(String shorterOne, String longerOne) {
		if (shorterOne == null || longerOne == null) {
			throw new IllegalArgumentException("One of or both arguments is/are null, this is not allowed");
		}

		if (shorterOne.length() > longerOne.length()) {
			return matcherAlgorithm.findGreatestMatch(longerOne, shorterOne);
		}

		return matcherAlgorithm.findGreatestMatch(shorterOne, longerOne);
	}

}
