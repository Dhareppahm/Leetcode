package leetcode.easy;

/** 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.
 *
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        String str = "A man, a plan, a canal: Panama";

        boolean isPalindrome = obj.isPalindrome(str);
        System.out.println(isPalindrome);
    }

    /**
     * Keep 2 pointers: i : from start of string 0 & j : from end of string
     * Skip special characters, as long as both characters are same proceed.
     * Return True, if all characters match. Return false as soon as there is a mismatch
     * Time Complexity: O(n)
     */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i < j){

            while(i < j && !Character.isLetterOrDigit(s.charAt(i)))     //proceed further if special character ignoring them
                i++;
            while(i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if(Character.toLowerCase(s.charAt(i)) !=
                    Character.toLowerCase(s.charAt(j))) {    //ignore case and comapre characters
                return false;
            }
            i++;                                            //move both pointers
            j--;
        }
        return true;
    }
}

