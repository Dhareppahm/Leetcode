package leetcode.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Exchanger;

/**
 https://leetcode.com/problems/evaluate-reverse-polish-notation/
 150. Evaluate Reverse Polish Notation

 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
-----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Push numbers into stack as you traverse. As soon as you find operator, pop twice, perform operation and push result
       into stack.
    2. Finally only answer will remain in stack
 */
public class EvaluateReversePolishNotation {

    Set<String> operators = new HashSet<String>() {{            //Set to hold the operators
        add("+");add("-");add("*");add("/");
    }};

    public static void main(String[] args) {
        EvaluateReversePolishNotation rpn = new EvaluateReversePolishNotation();
        String[] tokens = {"4", "13", "5", "/", "+"};//{"2", "1", "+", "3", "*"};
        int result = rpn.evalRPN(tokens);
        System.out.println(result);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {

            if(operators.contains(token)) {                 //if operator, pop twice, and push result in stack
                int num1 = stack.pop();
                int num2 = stack.pop();
                int result = evaluate(num1, num2, token);
                stack.push(result);
            }else{
                stack.push(Integer.parseInt(token));        //else push number in stack
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    private int evaluate(int num1, int num2, String operator){
        int result = 0;
        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num2 - num1; break;
            case "*": result = num1 * num2; break;
            case "/": result = num2 / num1; break;
        }
        return result;
    }
}
