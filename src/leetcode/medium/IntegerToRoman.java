package leetcode.medium;

/**
 https://leetcode.com/problems/integer-to-roman/
 12. Integer to Roman
 Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.
 -----------------------------------------------------------------------------------------------------------------------
Approach:
 1. In general - we take numbers from MSB and append to string
 2. Maintain special array for decimals and symbols. To handle subtractive, keep special values 900,400 & 90,40 & 9,4 in it
 3. Get quotient and append symbol for divisor.
 4. Subtract divisor from number, to remove MSB

 */
public class IntegerToRoman {

    //Taking these values in array is IMP
    private int[] decimals = {1000, 900, 500, 400,
                              100, 90, 50, 40,
                              10, 9, 5, 4,
                              1};

    private String[] romanSymbols = {"M", "CM", "D", "CD",
                                     "C", "XC", "L", "XL",
                                     "X", "IX", "V", "IV",
                                     "I"};

    public static void main(String[] args) {
        IntegerToRoman itr = new IntegerToRoman();
        int num = 3999;//99;//112;
        String roman = itr.intToRoman(num);
        System.out.println(roman);
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int i = 0;                                  //pointer to keep track of decimal values and their symbol
        while(num > 0){
            int quotient = num / decimals[i];       //eg num = 112. quotient will be 0, till decimal[i] = 100. Then quotient = 1
            for(int j = 0; j < quotient; j++){
                sb.append(romanSymbols[i]);         //i will be pointing to 100 decimal
                num = num - decimals[i];            //So 112 - 100 = 12.
            }
            i++;
        }
        return sb.toString();
    }


}
