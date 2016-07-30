package leetcode.medium;

/**
 29. Divide Two Integers
 https://leetcode.com/problems/divide-two-integers/

 Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:  https://discuss.leetcode.com/topic/45980/very-detailed-step-by-step-explanation-java-solution
 1. Check sign of dividend and divisor for  + or - If either but not both -ve then result will be negative.
    As we can't multiply by -1, (not to use *), return Two's complement

 2. Get absolute values into long to avoid overflow error
 3. Each loop iteration - divisor multiplied by 2 (using left shift)  to find largest divisor, So Log(n) complexity
 4. Right shift once, as we went 1 step further in above loop
 5. Subtract from dividend and check its still greater than divisor and repeat
 6. Each time we get multiple, we add it to final result (quotient)
 7. Return quotient based on sign and check Overflow condition
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        DivideTwoIntegers obj = new DivideTwoIntegers();
        int quotient = obj.divide(-2147483648, -1);
        System.out.println(quotient);
    }

    public int divide(int dividend, int divisor) {

        //check sign of dividend and divisor for  + or -
        boolean isNegative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            isNegative = true;
        }

        //Get absolute values into long to avoid overflow error
        long absDividend = Math.abs( (long) dividend);
        long absDivisor = Math.abs( (long) divisor);

        //each loop iteration - divisor multiplied by 2 (using left shift)
        long quotient = 0;
        while (absDividend >= absDivisor) {
            long temp = absDivisor;
            long multiple = 1;
            while (temp <= absDividend) {         //left shift dividor and multiple by 2
                temp = temp << 1;
                multiple = multiple << 1;
            }

            //if above loop we go 1 step further in check condition, so right shift once
            temp = temp >> 1;
            multiple = multiple >> 1;

            absDividend = absDividend - temp;       //subtract from dividend and check while
            quotient = quotient + multiple;         //add multiple to quotient
        }

        //return quotient
        if (isNegative) {
            return (int) ~quotient + 1;         //2's complement
        } else if (quotient > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) quotient;
        }

    }

}
