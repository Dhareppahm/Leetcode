package leetcode.easy;
/*
242. Valid Anagram
https://leetcode.com/problems/valid-anagram/
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

*/

public class ValidAnagram{

	public static void main(String[] args){
		ValidAnagram va = new ValidAnagram();

		String s = "anagram";
		String t = "nagarwm";

		boolean flag = va.isAnagram(s,t);
		System.out.println(flag);
	}

	/*
	Approach 1: O(n)
	 Check character counts, if each character count is same, then strings are anagrams

	 Approach 2: O(n log n)
	 Sort both strings and compare. IF equal then anagram else not
	*/
	public boolean isAnagram(String s, String t) {

		//Check length first -> anagrams are of same length
		if(s.length() != t.length())
			return false;

        //As we only have lowercase alphabets
        int[] table = new int[26];

        for(int i = 0; i < s.length(); i++){
        	char ch = s.charAt(i);
        	table[ch - 'a']++;
        }

        for(int i = 0; i < t.length(); i++){
        	char ch = t.charAt(i);
        	table[ch - 'a']--;						//if char was not present in s, then if its present in t, its freq will be -ve

        	if(table[ch - 'a'] < 0){			
        		return false;
        	}
        }

        return true;
    }


}