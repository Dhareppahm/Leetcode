package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 https://leetcode.com/problems/4sum/
 18. 4Sum

 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 Find all unique quadruplets in the array which gives the sum of target.

 Note: The solution set must not contain duplicate quadruplets.

 For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 -----------------------------------------------------------------------------------------------------------------------
 A typical k-sum problem. Time is N to the power of (k-1).

 Keep 1 outer loop and reduce rest of solution to 3-Sum problem

 Lower bound Ω(n^3) : https://discuss.leetcode.com/topic/27445/lower-bound-%CF%89-n-3
 */
public class FourSum {

    public static void main(String[] args) {
        FourSum obj = new FourSum();
        int[] arr = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = obj.fourSum(arr, target);
        //display
        for(List<Integer> res : result){
            System.out.println(Arrays.toString(res.toArray()));
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums == null || nums.length <= 3) return result;

        Arrays.sort(nums);
        for(int i=0; i < nums.length - 3; i++){
            if(i != 0 && nums[i] == nums[i-1])                      // Skip equal elements to avoid duplicates
                continue;

            for(int j=i+1; j < nums.length - 2; j++) {
                if(j != i+1 && nums[j] == nums[j-1])                // Skip equal elements to avoid duplicates
                    continue;

                int left = j+1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while(left+1 < right && nums[left] == nums[left+1]){    //Skip equal elements to avoid duplicates
                            left++;
                        }
                        while(right-1 > left && nums[right] == nums[right-1]){  //Skip equal elements to avoid duplicates
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                        while (left < right && nums[left] == nums[left-1]) {        // Skip equal elements to avoid duplicates
                            left++;
                        }
                    } else {
                        right--;
                        while (left < right && nums[right] == nums[right+1]) {     // Skip equal elements to avoid duplicates
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
}
