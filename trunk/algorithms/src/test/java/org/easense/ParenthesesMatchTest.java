package org.easense;

import org.easense.chapterone.collections.Stack;
import org.junit.Ignore;
import org.junit.Test;

import edu.princeton.StdIn;

public class ParenthesesMatchTest {

	@Test
	@Ignore
	public void testParentheses() {
		Stack<Character> stack;
		while (StdIn.isEmpty() == false) {
			boolean isMatched = true;
			stack = new Stack<Character>();
			String testSequence = StdIn.readLine();
			for (char oneChar : testSequence.toCharArray()) {
				if (oneChar == ' ') continue;
				if ("({[<".indexOf(oneChar) != -1) {
					stack.push(oneChar);
				} else if (")}]>".indexOf(oneChar) != -1) {
					char poped = stack.pop();
					if ("(){}[]<>".contains("" + poped + oneChar) == false) {
						isMatched = false;
						break;
					}
				}
			}
			
			System.out.println(testSequence + (isMatched ? " passed" : " failed"));
		}
	}
	
	@Test
	public void halfParentheses() {
		Stack<Character> stack;
		
		while (StdIn.isEmpty() == false) {
			stack = new Stack<Character>();
			String input = StdIn.readLine();
			int rpCount = 0;
			boolean isLastCharRp = true;
			// loop the string from end to start
			for (int i = input.length() - 1; i >= 0; i--) {
				char ch = input.charAt(i);
				if (ch == ')') {
					rpCount++;
					if (isLastCharRp == false) {
						char oldChar = stack.pop();
						stack.push('(');
						stack.push(oldChar);
						rpCount--;
					}
					isLastCharRp = true;
				} else {
					isLastCharRp = false;
				}
				stack.push(ch);
				if (i == 0) {
					while (rpCount > 0) {
						stack.push('(');
						rpCount--;
					}
				}
				
			}
			
			for (char ch : stack) {
				System.out.print(ch);
			}
			System.out.println();
		}
		
	}
}
