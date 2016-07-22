package leetcode.easy;

/**
 231. Power of Two
 https://leetcode.com/problems/power-of-two/
 Given an integer, write a function to determine if it is a power of two.
 ----------------------------------------------------------------------------------------------------------------------
 Powers of two in binary look like this:

 1: 0001
 2: 0010
 4: 0100
 8: 1000
 Note that there is always exactly 1 bit set. The only exception is with a signed integer. e.g. An 8-bit signed integer with a value of -128 looks like:

 10000000
 So after checking that the number is greater than zero, we can use a clever little bit hack to test that one and only one bit is set.

 x & x-1 should return 0, as only 1 bit must b set and after this it will be 0. so x will be 0

 bool isPowerOfTwo(int x) {
 return x > 0 && (x & x−1) == 0;
 }
 */
public class PowerOfTwo {

    public static void main(String[] args) {
        PowerOfTwo obj = new PowerOfTwo();
        int n = 16;
        boolean flag = obj.isPowerOfTwo(n);
        System.out.println(flag);
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n-1) == 0;
    }
}
