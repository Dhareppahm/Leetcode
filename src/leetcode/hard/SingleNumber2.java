package leetcode.hard;

import leetcode.easy.SingleNumber;

/**
 https://leetcode.com/problems/single-number-ii/
 137. Single Number II

 Given an array of integers, every element appears three times except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 -----------------------------------------------------------------------------------------------------------------------
 Approach 1:
    1. Take a count[32] for 32 bits of number. Store in it, sum of all i-th bits of all numbers given.
    2. If you sum the i-th bit of all numbers and mod 3, it must be either 0 or 1 due to the constraint of this problem
       where each number must appear either three times or once. This will be the ith bit of that "single number"
    3. After looping each bit, left shift the result of mod, and create the number.

    Eg: For input[] = {5,7,9,10,5,7,9,10,12,5,7,9,10};
        counts[] = {0,0,0,0.....,7,7,6,9};

        Thus, after % we get = 1100  (as 7%3 is 1, 6%3 is 0 and 9%3 is 0)
 */
public class SingleNumber2 {
    public static void main(String[] args) {

        SingleNumber2 obj = new SingleNumber2();

        int[] nums = {5,7,9,10,5,7,9,10,12,5,7,9,10};
        int ans = obj.singleNumber(nums);
        System.out.println(ans);
    }

    public int singleNumber(int nums[]) {
        int[] count = new int[32];                  //hold the sum of i-th bits of input numbers
        int result = 0;
        for (int i = 0; i < 32; i++) {              //Start with 0th bit, 1st-bit, 2nd-bit and so on
            for (int j = 0; j < nums.length; j++) {           //loop for all numbers in nums[]
                int number = nums[j];
                int numberRightShift = (number >> i);

                if ((numberRightShift & 1) == 1) {  //if i-th bit is 1, increment the count[i]
                    /*int cnt = count[i];
                    cnt++;
                    count[i] = cnt;*/
                    count[i]++;
                }
            }

//            int val =  count[i];
//            int valMod = val % 3;
            int valModLeftShift = (count[i] % 3) << i;      //recreate the sigle number from sum of i-th bits stored in count[]
            result = result | valModLeftShift;
        }
        return result;
    }
}
