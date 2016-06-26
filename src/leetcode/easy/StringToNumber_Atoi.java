package leetcode.easy;

/**
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement atoi to convert a string to an integer.
 *
 * Assumptions/Requirements:
 * 1. The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 *    Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 *    and interprets them as a numerical value.
 *
 * 2. The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * 3. If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists
 *    because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * 4. If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values,
 *    INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *
 */
public class StringToNumber_Atoi {

    public static void main(String[] args) {
        StringToNumber_Atoi obj = new StringToNumber_Atoi();

        String str = "2147483648";
        int number = obj.myAtoi(str);
        System.out.println(number);
    }

    public int myAtoi(String str){
        int i = 0, n = str.length();
        //Do nothing if whitespaces
        while(i < n && Character.isWhitespace(str.charAt(i))){
            i++;
        }

        int sign = 1;
        if( i < n && str.charAt(i) == '-'){
            sign = -1;
            i++;
        }else if(i < n && str.charAt(i) == '+'){
            sign = 1;
            i++;
        }

        int number = 0;
        while(i < n && Character.isDigit(str.charAt(i))){       //till sequence contains digit
            int digit = Character.getNumericValue(str.charAt(i));
            //overflow check: special case || for 2147483648 etc.
            if(number > Integer.MAX_VALUE/10 || (number == Integer.MAX_VALUE/10 && digit >= 8)){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            number = number * 10 + digit;
            i++;
        }
        return sign * number;
    }
}
