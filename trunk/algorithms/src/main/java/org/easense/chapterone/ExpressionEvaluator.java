package org.easense.chapterone;

import java.util.Stack;

public class ExpressionEvaluator {

	public static final String SUPPORTED_OPERATION = "+-*/";

	public static double evaluate(String expression) {
		if (expression == null || "".equals(expression.trim())) {
			throw new IllegalArgumentException("expression[" + expression + "] is empty");
		}

		// use two stacks to store operators and operated numbers separately
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> valueStack = new Stack<Double>();

		// firstly remove all the spaces in the expression and split expression
		// by space
		for (String exp : expression.split("\\s+")) {
			if ("".equals(exp) || "(".equals(exp)) {
				continue;
			}

			if (SUPPORTED_OPERATION.indexOf(exp) != -1) {
				operatorStack.push(exp);
			} else if (")".equals(exp)) {
				String op = operatorStack.pop();
				double value = valueStack.pop();
				if ("+".equals(op)) {
					value = valueStack.pop() + value;
				} else if ("-".equals(op)) {
					value = valueStack.pop() - value;
				} else if ("*".equals(op)) {
					value = valueStack.pop() * value;
				} else if ("/".equals(op)) {
					value = valueStack.pop() / value;
				}
				valueStack.push(value);

			} else {
				valueStack.push(Double.valueOf(exp));
			}
		}

		return valueStack.pop();
	}

	public static void main(String[] args) {
		String expression = "( 3 * ( ( 6 / 12 ) + 2 ) )";
		System.out.println(expression + " = " + evaluate(expression));
	}

}
