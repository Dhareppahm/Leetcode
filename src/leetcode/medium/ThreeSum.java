package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 https://leetcode.com/problems/3sum/
 15. 3Sum

 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 IMP: Sort input as we will use binary search logic
 1. For each input at index i
 2. Take pointers left = i+1, right = len-1;
 3. While left < right
        sum = num[i] + num[left] + num[right];
    If sum == 0
        left++; right--
    Else If sum < 0
        left++;
    Else
        right--;
 Each time check if num[left]==num[left+1] to skip duplicates
 Each time check if num[right]==num[right-1] to skip duplicates


 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>>  result = obj.threeSum(nums);
        for(List<Integer> res : result){
            System.out.println(Arrays.toString(res.toArray()));
        }

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums.length <= 2) return result;      //Edge case

        Arrays.sort(nums);                      //Sort the inputs, as we use logic to increment start and end ptr
        for(int i=0; i < nums.length; i++){

            if(i-1 >= 0 && nums[i] == nums[i-1]){   //Skip equal elements to avoid duplicates
                continue;
            }

            int left = i+1;
            int right = nums.length-1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while(left+1 < right && nums[left] == nums[left+1]){    //Skip equal elements to avoid duplicates
                        left++;
                    }
                    while(right-1 > left && nums[right] == nums[right-1]){  //Skip equal elements to avoid duplicates
                        right--;
                    }

                    left++;
                    right--;
                }else if(sum < 0){
                    left++;
                    while (left < right && nums[left] == nums[left-1]) {        // Skip equal elements to avoid duplicates
                        left++;
                    }
                }else{
                    right--;
                    while (left < right && nums[right] == nums[right+1]) {     // Skip equal elements to avoid duplicates
                        right--;
                    }
                }
            }
        }

        return result;
    }


}
