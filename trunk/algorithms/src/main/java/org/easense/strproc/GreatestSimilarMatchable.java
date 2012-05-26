package org.easense.strproc;

public interface GreatestSimilarMatchable {
	
	/**
	 * Find the greatest similar string between the given parameters (both are not null allowed)<br/>
	 * eg. <code>findGreatestMatch(absimilar, xdegsimilarxsgei) = similar</code>
	 * @param shorterOne can't be null
	 * @param longerOne can't be null
	 * @return the greatest similar string
	 */
	String findGreatestMatch(String shorterOne, String longerOne);
}
