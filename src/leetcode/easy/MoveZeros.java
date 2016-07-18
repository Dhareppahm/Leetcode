package leetcode.easy;

import java.util.Arrays;
/**
 https://leetcode.com/problems/move-zeroes/
 283. Move Zeroes
 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 -------------------------------------------------------------------------------------------------------------------------
 Approaches:
    A. Brute Force:
        2 pointers, if nums[i] == 0, then using nonZeroPtr find next non-zero number and swap with nums[i]
        In Worst case, we need to traverse till end of array in second loop, Complexity O(n^2)

    B. Optimized Approach:
        Count total-zeros, if non-zero the move it to nums[i - totalZeros]
        Next start from (nums.length - totalZeros) and insert 0
        Complexity: O(n)

 */
public class MoveZeros {

    public static void main(String[] args){
        MoveZeros obj = new MoveZeros();
        int[] nums = {0,0,0,0,0,0,0,0,0,0,0,1,0};//{1, 0, 3, 12,0,0,0,0,0,0,0,0};
         obj.moveZeroes_Optimal(nums);
         obj.moveZeroes_BruteForce(nums);
    }

    /*
        Brute Force : O(n^2)
        2 pointers, if nums[i] == 0, then using nonZeroPtr find next non-zero number and swap with 0
    */
    public void moveZeroes_BruteForce(int[] nums) {

        int i=0, nonZeroPtr = 0;
        while(i < nums.length){

            nonZeroPtr = i+1;

            if(nums[i] == 0){

                //find next non-zero number and swap
                while(nonZeroPtr < nums.length){
                    if(nums[nonZeroPtr] != 0){
                        //swap
                        int temp = nums[i];
                        nums[i] = nums[nonZeroPtr];
                        nums[nonZeroPtr] = temp;
                        break;
                    }
                    nonZeroPtr++;
                }
            }

            i++;
        }

        System.out.println(Arrays.toString(nums));
    }

    /**
     * Optimized Approach:
         Count total-zeros, if non-zero the move it to nums[i - totalZeros]
         Next start from (nums.length - totalZeros) and insert 0
         Complexity: O(n)
     */
    public void moveZeroes_Optimal(int[] nums) {
        int totalZeros = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                totalZeros++;							//increment total zeros count
            }else{
                nums[i - totalZeros] = nums[i];			//insert non-zero numbers at correct position in-place of zeros
            }
        }

        for(int i = (n - totalZeros); i < n; i++){		//insert 0's after non-zero numbers
            nums[i] = 0;
        }

        System.out.println(Arrays.toString(nums));
    }
}
