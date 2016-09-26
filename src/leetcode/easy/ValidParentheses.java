package leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 20. Valid Parentheses
 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Take a stack, if any opening bracket, push into stack.
    2. If any closing bracket, pop from stack and check if matching pair found. If not return false.
    3. If stack empty at end, return true
 */
public class ValidParentheses {

    Map<Character, Character> bracketPairs = new HashMap<Character, Character>(){{
        put('(',')'); put('{','}'); put('[',']');
    }};

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        String s = "[()]";
        boolean valid = vp.isValid(s);
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i < s.length(); i++){
            if(bracketPairs.containsKey(s.charAt(i))){                                      //opening = push
                stack.push(s.charAt(i));
            }else if(stack.isEmpty() || bracketPairs.get(stack.pop()) != s.charAt(i)){      //check empty for case "]"
                return false;                                                               //closing = pop & check
            }
        }

        return stack.isEmpty() ? true : false;
    }
}
