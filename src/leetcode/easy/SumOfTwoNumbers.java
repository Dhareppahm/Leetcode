package leetcode.easy;

/**
 https://leetcode.com/problems/sum-of-two-integers/
 https://discuss.leetcode.com/topic/49771/java-simple-easy-understand-solution-with-explanation/2
 371. Sum of Two Integers

 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example:
 Given a = 1 and b = 2, return 3
 -----------------------------------------------------------------------------------------------------------------------
 Approach:

     "&" AND operation, for example, 2 (0010) & 7 (0111) => 2 (0010)

     "^" XOR operation, for example, 2 (0010) ^ 7 (0111) => 5 (0101)

     "~" NOT operation, for example, ~2(0010) => -3 (1101) what??? Don't get frustrated here. It's called two's complement.

     1111 is -1, in two's complement
     1110 is -2, which is ~2 + 1, ~0010 => 1101, 1101 + 1 = 1110 => 2
     1101 is -3, which is ~3 + 1

     so if you want to get a negative number, you can simply do ~x + 1

     Reference:
     https://en.wikipedia.org/wiki/Two%27s_complement
     https://www.cs.cornell.edu/~tomf/notes/cps104/twoscomp.html

    1. For this, problem, for example, we have a = 1, b = 3,
    2. In bit representation, a = 0001, b = 0011,
    3. First, we can use "and"("&") operation between a and b to find a carry.
    4. carry = a & b, then carry = 0001
    5. Second, we can use "xor" ("^") operation between a and b to find the different bit, and assign it to a,
    6. Then, we shift carry one position left and assign it to b, b = 0010.
    Iterate until there is no carry (or b == 0)

 */
public class SumOfTwoNumbers {

    public static void main(String[] args) {
        int a = 4;
        int b = 5;

        int sum1 = addIterative(a, b);
        System.out.println("Sum by iterative method : " + sum1);

        int sum2 = addRecursion(a,b);
        System.out.println("Sum by recursive method : " + sum2);

        int minus1 = subIterative(a,b);
        System.out.println("Subtraction by iterative method : "+minus1);

        int minus2 = subRecursion(a,b);
        System.out.println("Subtraction by iterative method : "+minus2);
    }

    /*******************************************************************************************************************
     *                                          ADDING TWO INTEGERS
     *******************************************************************************************************************/

    private static int addIterative(int a, int b) {
        //iterate till second no becomes 0
        while(b != 0){
            // carry now contains common set bits of 'a' and 'b'
            int carry = a & b;

            // Sum of bits of 'a' and 'b' where at least one of the bits is not set
            a = a ^ b;

            // Carry is shifted by one so that adding it to 'a' gives the required sum
            b = carry << 1;
        }
        return a;
    }

    private static int addRecursion(int a, int b) {
        if(b == 0){
            return a;
        }else{
            //Pass Sum, Carry shifted by 1 recursively
            return addRecursion(a ^ b, (a & b) << 1);
        }
    }

    /*******************************************************************************************************************
     *                                          SUBTRACTING TWO INTEGERS
     *******************************************************************************************************************/

    private static int subIterative(int a, int b) {

        //iterate till second no becomes 0
        while(b != 0){
            // carry now contains common set bits of 'a' and 'b'
            int borrow = (~a) & b;

            // Subtraction of bits of 'a' and 'b' where at least one of the bits is not set
            a = a ^ b;

            // Borrow is shifted by one so that subtracting it from 'a' gives the required sum
            b = borrow << 1;
        }
        return a;
    }

    private static int subRecursion(int a, int b) {
        if(b == 0){
            return a;
        }else{
            //Pass Sum, Carry shifted by 1 recursively
            return subRecursion(a ^ b, (~a & b) << 1);
        }
    }

}
