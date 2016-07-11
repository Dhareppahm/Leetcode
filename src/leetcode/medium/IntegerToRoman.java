package leetcode.medium;

/**
 https://leetcode.com/problems/integer-to-roman/
 12. Integer to Roman
 Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.
 -----------------------------------------------------------------------------------------------------------------------
Roman   -   Decimal
 I      -   1
 V      -   5
 X      -   10
 L      -   50
 C      -   100
 D      -   500
 M      -   1000

 */
public class IntegerToRoman {
    public static void main(String[] args) {
        IntegerToRoman itr = new IntegerToRoman();

        int num = 12;
        String roman = itr.intToRoman(num);
        System.out.println("Integer = "+ num + " Roman = "+ roman);
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        while(num > 0){

        }

        return sb.toString();
    }
}
