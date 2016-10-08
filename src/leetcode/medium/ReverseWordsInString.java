package leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * 151. Reverse Words in a String
 *
 * Given an input string, reverse the string word by word.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".
------------------------------------------------------------------------------------------------------------
 Two Pass Approach:
 1. Split input string by space and store in string array
 2. Reverse string array: by two pointer approach and swap string(words) instead like previous characters
 3. Loop through array and append to string

 One pass approach:
 1. While iterating the string in reverse order, we keep track of a word’s begin and end position.
 2. When we are at the beginning of a word, we append it.

 Edge Test Cases:
 1. Input: " "      Output: ""
 2. Input: "1 "     Output: "1"
 3. Input: " 1"     Output: "1"
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        ReverseWordsInString obj = new ReverseWordsInString();

        String input = "the sky is blue";
        System.out.println("Input: "+input);

        System.out.println("Two Pass Solution:");
        String reverse = obj.reverseWords2(input);
        System.out.println(reverse);

        System.out.println("One Pass Solution:");
        reverse = obj.reverseWords1(input);
        System.out.println(reverse);
    }

    /**
     * Two-pass solution: First pass to split the string by spaces into an array of words,
     *                    then second pass to extract the words in reversed order.
     */
    public String reverseWords2(String s) {
        String[] array = s.split(" ");
        int i=0, j=array.length-1;
        while(i < j){
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            i++;
            j--;
        }

        //Convert reversed array to string
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < array.length; k++){
            sb.append(array[k]);
            if(k+1 < array.length){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Can we do it in 1 pass?
     * One Pass Solution:
     *      Start from end, While iterating the string in reverse order, we keep track of a word’s begin and end position.
     *      When we are at the beginning of a word, we append it.
     */
    public String reverseWords1(String s){
        StringBuilder sb = new StringBuilder();

        int j = s.length();
        for(int i = s.length()-1; i >= 0; i--){
            if (s.charAt(i) == ' ') {                   //reset end of word pointer
                j = i;
            } else if (i == 0 || s.charAt(i - 1) == ' ') {  //append word in string
                if (sb.length() != 0) {                     //handle: " ", " 1", "1 "
                    sb.append(' ');
                }
                sb.append(s.substring(i, j));
            }
        }
        return sb.toString();
    }
}
