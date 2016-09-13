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
        int j=0;                                 //one pointer to 0 and

        for(int i=1; i < nums.length; i++) {     //another pointer to 1 to start and check with j
            if(nums[j] != nums[i]){             //if current value not equals to first pointer value
                nums[++j] = nums[i];            //replace first pointer value with current value
            }
                                                //if values same, let loop continue
        }
        System.out.println(Arrays.toString(nums));
        return j+1;
    }
}
