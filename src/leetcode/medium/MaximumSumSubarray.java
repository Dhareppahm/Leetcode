package leetcode.medium;

import java.util.Arrays;

/**
 https://leetcode.com/problems/maximum-subarray/
 53. Maximum Subarray

 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 the contiguous subarray [4,−1,2,1] has the largest sum = 6.

 click to show more practice.

 More practice:
 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 ----------------------------------------------------------------------------------------------------------------------------------
 Approach 1: Dynamic Programming => Time Complexity : O(n), Space Complexity: O(1)
    1. Keep track of current max & global max sum       //Memoization
    2. current max = MAX(A[i], A[i] + current max)
    3. Update global max if current max becomes greater

    Note: If we have to return the subarray, keep track of start and end pointers of subarray
    4. Take startIndex, maxStart, maxEnd
    5. Update startIndex when input number, A[i] is larger
    5. Move maxEnd further when a new global max is found, update maxStart to startIndex
    6. Return subarray using maxStart and maxEnd
 */
public class MaximumSumSubarray {

    public static void main(String[] args) {
        MaximumSumSubarray obj = new MaximumSumSubarray();

        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = obj.maxSubArray(input);
        System.out.println(maxSum);

        int[] subArray = obj.maxSubArray_ReturnSubArray(input);
        System.out.println(Arrays.toString(subArray));
    }

    /**
     * Kadane's Algorithm
     *
     * @return maxSum from subarray
     */
    public int maxSubArray(int[] A) {
        int maxEndingHere = A[0];           //Store max till current index
        int maxSoFar = A[0];                //Store global max. Will contain final max sum

        for(int i=1; i < A.length; i++){
            maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * Kadane's Algorithm: Modify above program to return the array elements
     *
     * @return the subarray with max sum
     */
    public int[] maxSubArray_ReturnSubArray(int[] A){
        int maxEndingHere = A[0];           //Store max till current index
        int maxSoFar = A[0];                //Store global max. Will contain final max sum

        int maxStart = 0, maxEnd = 0;       //Store index of start and end of max subarray
        int startIndex = 0;

        for(int i=1; i < A.length; i++){

            if(A[i] > maxEndingHere + A[i]){
                maxEndingHere = A[i];
                startIndex = i;
            }else{
                maxEndingHere = maxEndingHere + A[i];
            }

            if(maxEndingHere > maxSoFar){               //update maxSoFar if its smaller
                maxSoFar = maxEndingHere;
                maxStart = startIndex;
                maxEnd = i;                             //move maxEnd further
            }
        }
        System.out.println("Max sum = "+maxSoFar);
        return Arrays.copyOfRange(A, maxStart, maxEnd+1);
    }

}
