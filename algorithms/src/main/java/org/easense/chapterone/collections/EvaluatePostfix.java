package org.easense.chapterone.collections;

public class EvaluatePostfix {
	
	/**
	 * evaluate to a postfix expression, which has space between words to have a better form
	 * for example: 3 4 + 12 *
	 * @param postfixExpr
	 * @return
	 */
	public static double evaluate(String postfixExpr) {
		Stack<Double> exprStack = new Stack<Double>();
		for (String element : postfixExpr.split("\\s+")) {
			// skip the empty element expression
			if (element == null || element.matches("//s*")) {
				continue;
			}
			if (element.matches("\\d+")) {
				exprStack.push(Double.valueOf(element));
			} else {
				// get the two operands from top of the stack
				double operandOne = exprStack.pop();
				double operandTwo = exprStack.pop();
				
				// add operation
				if ("+".equals(element)) {
					exprStack.push(operandOne + operandTwo);
				} else if ("-".equals(element)) {
					exprStack.push(operandOne - operandTwo);
				} else if ("*".equals(element)) {
					exprStack.push(operandOne * operandTwo);
				} else if ("/".equals(element)) {
					exprStack.push(operandOne / operandTwo);
				}
			}
		}
		return exprStack.pop();
	}
	
	public static void main(String[] args) {
		String postfixExpression = "7 16 16 16 * * * 5 16 16 * * 3 16 * 1 + + +";
		System.out.println(postfixExpression + " = " + evaluate(postfixExpression));
	}
}
