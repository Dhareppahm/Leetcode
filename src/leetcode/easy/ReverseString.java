package leetcode.easy;

import java.util.Stack;

/**
Problem:
Write a function that takes a string as input and returns the string reversed.
Example:
Given s = "hello", return "olleh".
 */
public class ReverseString {
	public static void main(String[] args) {
		ReverseString obj = new ReverseString();
		String s = "hello world";

		System.out.println(obj.reverseString_ByTwoPtrs(s));
        System.out.println(obj.reverseString_ByLoop(s));
        System.out.println(obj.reverseString_ByStringBuilder(s));
        System.out.println(obj.reverseString_ByStack(s));
	}

	//1. Using Two pointers: start and end
	public String reverseString_ByTwoPtrs(String s) {
		if(s == null){
			return null;
		}
		char[] str = s.toCharArray();	//convert String to char array
		int start = 0;					//pointers to start and end of string
		int end = str.length - 1;

		while(start < end){				//loop till start < end and swap
			char temp = str[start];				
			str[start] = str[end]; 
			str[end] = temp;
			start++;					//increment start & decrement end
			end--;
		}
		
		/* Using For Loop
		 * for (int i = 0, j = str.length-1; i < j; i++, j--) {
			char temp = str[i];
			str[i] = str[j];
			str[j] = temp;
		}*/
		return new String(str);			//Time complexity: O(n), Space complexity: O(n) for char array
	}
	
	//2. Loop till middle of String
	public String reverseString_ByLoop(String s) {
		if(s == null){
			return null;
		}
		char[] str = s.toCharArray();
		int len = str.length;			//Don't do -1 here else len/2 will not work for string of len = 2
		
		for(int i=0; i < len/2; i++){	//Loop till middle and swap
			char temp = str[i];
			str[i] = str[len-1-i];		//Do -1 here to handle ArrayIndexOutOfBounds
			str[len-1-i] = temp;
		}		
		return new String(str);
	}
	
	
	//Using StringBuilder
	public String reverseString_ByStringBuilder(String s){
		if(s == null){
			return null;
		}		
		//return new StringBuilder(s).reverse().toString();			//1 liner code using inbuilt function
		
		StringBuilder sb = new StringBuilder();
		for(int i = s.length()-1; i >= 0; i--){			//Loop from end and append to StringBuilder
			sb.append(s.charAt(i));
		}		
		return sb.toString();
	}
	
	//Using Stack
	public String reverseString_ByStack(String s){
		if(s == null){
			return null;
		}
		Stack<Character> stack = new Stack<Character>();	//Push all characters to stack
		for(int i=0; i < s.length(); i++){
			stack.push(s.charAt(i));
		}
		StringBuilder sb = new StringBuilder();				//Pop and append characters from stack
		while (!stack.isEmpty()) {
			sb.append(stack.pop());			
		}
		return sb.toString();		
	}	
}
