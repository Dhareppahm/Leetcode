package leetcode.easy;

import java.util.*;

/**
 https://leetcode.com/problems/plus-one/

 http://www.programcreek.com/2014/05/leetcode-plus-one-java/

 Given a non-negative number represented as an array of digits, plus one to the number.
 The digits are stored such that the most significant digit is at the head of the list.

 Example Questions Candidate Might Ask:
 Q: Could the number be negative?
 A: No. Assume it is a non-negative number.
 Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or [2,1]?
 A: The digits are stored such that the most significant digit is at the head of the list.
 Q: Could the number contain leading zeros, such as [0,0,1]?
 A: No.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. To solve this problem, we can use a flag to mark if the current digit needs to be changed
    2. If carry is set, copy to digits array and put carry in MSB
    3. Else return digits
 */
public class PlusOne {
    public static void main(String[] args) {
        PlusOne po = new PlusOne();

        int[] digits = {};
        int[] result = po.plusOne(digits);
        System.out.println(Arrays.toString(result));
    }

    public int[] plusOne(int[] digits) {

        int carry = 1;                      //start with carry 1 as we want to add 1
        for(int i=digits.length-1; i >= 0; i--){
            int sum = digits[i] + carry;
            carry = sum / 10;
            sum = sum % 10;

            digits[i] = sum;
        }

        //if carry remaining, create new array and add carry to it
        if(carry > 0){
            int[] result = new int[digits.length+1];
            result[0] = carry;
            for(int i=0; i < digits.length; i++){           //copy everything from digits to result
                result[i+1] = digits[i];
            }
            return result;
        }
        return digits;
    }
}
