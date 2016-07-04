package leetcode.easy;

/**
 * https://leetcode.com/problems/reverse-integer/
 *
 * Reverse digits of an integer.
 Example1: x = 123, return 321
 Example2: x = -123, return -321
 ---------------------------------------------------------------------------------------------------
 Approach:
    While number > 0
        //Check overflow in reverse number
        Reverse = Reverse * 10 + number % 10;
        number = number / 10;
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();

        int x = 100000003;
        int reverse = ri.reverse(x);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        int reverse = 0;
        boolean isNegative = false;

        if(x < 0){                                  //Handle -ve input
            isNegative = true;
            x = Math.abs(x);
        }

        while(x > 0){                               //Reverse process
            if(reverse > (Integer.MAX_VALUE/10)){   //IMP: Check overflow before multiply by 10
                return 0;
            }
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }

        return isNegative ? reverse * -1 : reverse;
    }

}
