package leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Similar to Question [6. Reverse Words in a String], but with the following constraints:
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * Could you do it in-place without allocating extra space?
 *
 Approach:
 O(n) runtime, O(1) space – In-place reverse:
 Let us indicate the ith word by wi and its reversal as wi′. Notice that when you reverse a word twice, you get back the original word.
 That is, (wi′)′ = wi.

 The input string is w1 w2 … wn. If we reverse the entire string, it becomes wn′ … w2′ w1′.
 Finally, we reverse each individual word and it becomes wn … w2 w1.

 Similarly, the same result could be reached by reversing each individual word first, and then reverse the entire string.
 */
public class ReverseWordsInString2 {
    public static void main(String[] args) {
        ReverseWordsInString2 obj = new ReverseWordsInString2();

        String input = "the sky is blue";
        String reverse = obj.reverseWords(input.toCharArray());
        System.out.println(reverse);
    }

    public String reverseWords(char[] s){
        //1. reverse entire string      i.e eulb si yks eht
        reverse(s, 0, s.length);
        System.out.println(Arrays.toString(s));

        //2. reverse words
        int i=0;                            //i is prev pointer
        for(int j=0; j <= s.length; j++){
            if(j == s.length || s[j] == ' '){
                reverse(s, i, j);
                i = j+1;
            }
        }
        System.out.println(Arrays.toString(s));

        return String.valueOf(s);
    }

    //Using Loop till middle of String approach
    private void reverse(char[] s, int begin, int end) {
        for (int i = 0; i < (end - begin) / 2; i++) {
            char temp = s[begin + i];
            s[begin + i] = s[end - i - 1];
            s[end - i - 1] = temp;
        }
    }

}
