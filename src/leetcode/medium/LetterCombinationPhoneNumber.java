package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 17. Letter Combinations of a Phone Number
 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.

 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 -----------------------------------------------------------------------------------------------------------------------
 Approach by Recursion:
 1. Base case: if index == digits.length, then add prefix to list
 2. Get letters for digit using map
 for each index in letters, combine letters with each letters in digits at next index           //IMP

 */
public class LetterCombinationPhoneNumber {

    String[] digitLettersMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        LetterCombinationPhoneNumber obj = new LetterCombinationPhoneNumber();
        String digits = "56";
        List<String> result = obj.letterCombinations(digits);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if(digits == null || digits.length() == 0) return list;

        helper(digits, "", 0, list);
        return list;
    }

    private void helper(String digits, String prefix, int index, List<String> list) {
        if(index == digits.length()){           //Base case
            list.add(prefix);
            return;
        }

        //Get letters for each digit and combine them
        String letters = digitLettersMap[Integer.parseInt(""+digits.charAt(index))];
        for(int i=0; i < letters.length(); i++){
            helper(digits, prefix + letters.charAt(i), index + 1, list);
        }
    }
}
