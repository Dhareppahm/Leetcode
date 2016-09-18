package leetcode.easy;

import java.util.Arrays;

/**
 26. Remove Duplicates from Sorted Array
 https://leetcode.com/problems/remove-duplicates-from-sorted-array/

 Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 For example,
 Given input array nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

 */
public class RemoveDuplicatesfromSortedArray {

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray obj = new RemoveDuplicatesfromSortedArray();
        int[] nums = {1,1,2};
        int len = obj.removeDuplicates(nums);
        System.out.println(len);
    }

    /**
     * Two Pointer approach
     */
    public int removeDuplicates(int[] nums) {
        int prev=0;                                 //prev pointer start from 0 and

        for(int i=1; i < nums.length; i++) {       //i-th pointer start from 1 and check with prev
            if(nums[prev] != nums[i]){             //if current value not equals to prev pointer value
                nums[++prev] = nums[i];            //increment prev and replace prev pointer value with current value
            }
                                                //if values same, let loop continue
        }
        System.out.println(Arrays.toString(nums));
        return prev+1;
    }
}
