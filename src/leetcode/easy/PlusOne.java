package leetcode.easy;

import java.util.*;

/**
 * https://leetcode.com/problems/plus-one/
 *
 * http://www.programcreek.com/2014/05/leetcode-plus-one-java/
 *
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

        int[] digits = {9,9};
        int[] result = po.plusOne(digits);
        System.out.println(Arrays.toString(result));
    }

    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length==0){     //if empty input
            return new int[0];
        }

        int carry = 1;                              //set to 1 as we need to plus 1
        for(int i = digits.length-1; i >= 0; i--){  //sum and carry logic
            int sum = digits[i] + carry;
            if(sum >= 10){
                carry = 1;
            }else{
                carry = 0;
            }
            digits[i] = sum % 10;
        }

        //If carry == 1, we need to put carry in new array of digits.length+1
        if(carry == 1){
            int[] result = new int[digits.length+1];
            System.arraycopy(digits, 0, result, 1, digits.length);      //copy from 1 index as we have to put carry at [0]
            result[0] = carry;
            return result;
        }else{
            return digits;
        }

    }
}
