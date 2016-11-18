package leetcode.medium;

import java.util.Arrays;

/**
 43. Multiply Strings
 https://leetcode.com/problems/multiply-strings/

 Given two numbers represented as strings, return multiplication of the numbers as a string.

 Note:
 The numbers can be arbitrarily large and are non-negative.
 Converting the input string to integer is NOT allowed.
 You should NOT use internal library such as BigInteger.
 ----------------------------------------------------------------------------------------------
 Approach:
 Eg:     123
     x    45
    ----------
    5  10  15
 4  8  12
 --------------
 4  13  22  15  => Reverse strings and Store these in sum-of-products array

 Append the sums last digit to string and move carry i.e do summation but considering its places
 Reverse the result string and return answer

 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "45";

        MultiplyStrings ms = new MultiplyStrings();
        String result = ms.multiply(num1,num2);
        System.out.println(result);
    }

    public String multiply(String num1, String num2) {
        String rev1 = new StringBuilder(num1).reverse().toString();
        String rev2 = new StringBuilder(num2).reverse().toString();

        int[] productSums = new int[num1.length() + num2.length()];        //store sum of products

        for(int i=0; i < rev1.length(); i++){
            for(int j=0; j < rev2.length(); j++){
                int val = (rev1.charAt(i) - '0') * (rev2.charAt(j) - '0');
                productSums[i+j] += val;
            }
        }

        System.out.println(Arrays.toString(productSums));

        int carry=0;
        StringBuilder sb = new StringBuilder();

        for(int val : productSums){
            val = val + carry;
            carry = val / 10;
            val = val % 10;

            sb.append(val);
        }

        //Remove front zero in reversed string if present
        String result = sb.reverse().toString();
        if(result.charAt(0) == '0'){
            return result.substring(1);
        }else{
            return result;
        }

    }

}
