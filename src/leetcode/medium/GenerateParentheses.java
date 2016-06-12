package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
Subs
 *
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		GenerateParentheses obj = new GenerateParentheses();
		int n = 3;
		List<String> answers = obj.generateParenthesis(n);		
		//Print answers
		for(String ans : answers){
			System.out.println(ans);
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		
		//Recursive helper function: pass list, start empty string, n, openCount = 0, closeCount = 0
		helper(list, "", n, 0, 0);
		return list;
	}
	
	private void helper(List<String> list, String str, int n, int openCount, int closeCount){
		
		//Base condition: If openCount == n && closeCount == n, get final string and add to list
		if(openCount == n && closeCount == n){
			list.add(str);
			return;
		}
		
		//If openCount < n, we need to add opening bracket 
		if(openCount < n){
			helper(list, str+"(", n, openCount+1, closeCount);
		}
		
		//If more opening bracket than closing, then add a closing bracket
		if(openCount > closeCount){
			helper(list, str+")", n, openCount, closeCount+1);
		}
		
	}
	
}
