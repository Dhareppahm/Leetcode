package leetcode.medium;

import java.util.Arrays;

/**
 16. 3Sum Closest
 https://leetcode.com/problems/3sum-closest/

 Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 Return the sum of the three integers. You may assume that each input would have exactly one solution.

 For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 ----------------------------------------------------------------------------------------------------------------------
 Approach:
 Sort input as we will use binary search logic
 1. For each input at index i
 2. Take pointers left = i+1, right = len-1;
 3. While left < right
 sum = num[i] + num[left] + num[right];
 If target - sum < prevDiff
     update prevDiff and result to sum
 Else If sum < target
    left++;
 Else
    right--;

 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest obj = new ThreeSumClosest();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = obj.threeSumClosest(nums, target);
        System.out.println(result);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);              //sort input to check like binary search
        int prevDiff = Integer.MAX_VALUE;
        int result = 0;

        for(int i=0; i < nums.length; i++){
            int left = i+1;
            int right = nums.length - 1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(target - sum);

                if(diff == 0) return sum;

                if(diff < prevDiff){
                    prevDiff = diff;
                    result = sum;
                }else if(sum < target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return result;
    }

}
