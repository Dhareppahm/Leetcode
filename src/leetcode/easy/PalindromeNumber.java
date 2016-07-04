package leetcode.easy;

/**
 * https://leetcode.com/problems/palindrome-number/
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * Some hints:
 Could negative integers be palindromes? (ie, -1) Assume: -ve is not palindrome

 If you are thinking of converting the integer to string, note the restriction of using extra space.

 You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

 There is a more generic way of solving this problem.
 * --------------------------------------------------------------------------------------------------------------------
 Approach: (Use 2 pointer approach as used in String, but we have constraint not to use extra space so work with integer)
    a. Take a int variable, of same no.of zeros of number
    b. Take leftmost digit and right most digit, and compare
    c. Remove left and right most number, reduce variable by 100 and continue till number != 0

 Trick to get left most number:
 1. firstDigit = number/((int)(pow(10,(int)log(number))));

 This should get your first digit using math instead of strings.

 In your example log(543) = 2.73 which casted to an int is 2. pow(10, 2) = 100 543/100 = 5.43 but since it's an int it gets truncated to 5

 2. Almost certainly more efficient than using Strings:

 int firstDigit(int x) {
     while (x > 9) {
        x /= 10;
     }
     return x;
 }
 (Works only for nonnegative integers.)

 */
public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();

        int num = 1000021;
        boolean flag = pn.isPalindrome(num);
        System.out.println(flag);
    }

    public boolean isPalindrome(int x) {
        if(x < 0){                  //Check if -ve => Not a Palindrome
            return false;
        }
        int div = 1;                //div multiplied by 10, till same no.of digits as num
        while(x /div >= 10){        //initialize div to same no.of digits eg. if x = 121, div = 100, so we get leftmost digit
            div *= 10;
        }

        while(x != 0) {
            int rightDigit = x % 10;
            int leftDigit = x / div;

            if(leftDigit != rightDigit){
                return false;
            }

            x = x % div;            //remove leftmost digit
            x = x / 10;             //remove rightmost digit

            div /= 100;             //as left and right most digits gone, so divide by 100
        }

        return true;
    }

    //OR another way of finding leftmost digit
    private int getLeftmostDigit(int num){
        while(num > 9){
            num /= 10;
        }
        return num;
    }
}
