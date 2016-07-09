package leetcode.easy;

/**
 https://leetcode.com/problems/single-number/
 136. Single Number

 Given an array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 ----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Use HashMap<Integer, Integer> storing Number and Frequency. Iterate and return number with frequency 1. O(n) space
    2. Or Use HashSet<Integer> when number found again, remove it from set, leaving atleast with only 1 number which occurs once
    3. Using "every element appears twice except for one" property:
        => XOR-ing a number with itself is zero.
        => If we XOR all numbers together, it would effectively cancel out all elements that appear twice leaving us with
           only the single number.

 */
public class SingleNumber {
    public static void main(String[] args) {
        SingleNumber obj = new SingleNumber();

        int[] nums = {1,2,3,4,5,6,5,4,3,2,1};
        int singleNumber = obj.singleNumber(nums);
        System.out.println("Single Number = "+ singleNumber);
    }

    public int singleNumber(int[] nums) {
        int num = 0;
        for(int x : nums){
            num = num ^ x;                  //XOR-ing a number with itself is zero
        }
        return num;
    }
}
