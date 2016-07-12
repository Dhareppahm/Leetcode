package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 13. Roman to Integer
 https://leetcode.com/problems/roman-to-integer/
 Given a roman numeral, convert it to an integer.

 Input is guaranteed to be within the range from 1 to 3999.
-----------------------------------------------------------------------------------------------------------------------
Approach:
    1. Maintain a map of <Main Roman characters, Decimals Values>
    2. Check value from map, also keep track of previous value.
    3. If previousValue < currentValue, then its subtractive
    4. Else its additive
 */
public class RomanToInteger {

    //Taking these values in array is IMP
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1); put('V', 5); put('X', 10); put('L', 50); put('C', 100); put('D', 500); put('M', 1000);
    }};

    public static void main(String[] args) {
        RomanToInteger rti = new RomanToInteger();

        String roman = "MMMCMXCIX";//"MMMCMXCIX";//"CXII";//XCIX
        int number = rti.romanToInt(roman);
        System.out.println(number);
    }

    public int romanToInt(String s) {
        int num = 0;
        int previousVal = 0;
        for(int i=0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            if(previousVal < val) {                                      //Core Logic : special case of subtraction
                num = (num - previousVal) + (val - previousVal);        //i.e num += val - 2 * previousVal
            } else {
                num = num + val;
            }

            previousVal = val;
        }
        return num;
    }
}
