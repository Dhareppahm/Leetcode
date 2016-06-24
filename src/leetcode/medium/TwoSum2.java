package leetcode.medium;

import java.util.Arrays;
/*
	167.	Two Sum II - Input array is sorted
	Question:
    Similar to Question [1. Two Sum], except that the input array is already sorted in ascending order
*/
public class TwoSum2 {
    public static void main(String[] args){
        int[] nums = {1,2,4,6,7,11};
        int target = 9;

        TwoSum2 obj = new TwoSum2();

        System.out.println("Using Binary Search:");
        int[] result = obj.twoSum2_BinarySearch(nums, target);
        System.out.println(Arrays.toString(result));

        System.out.println("Using 2 variables:");
        int[] result1 = obj.twoSum2_UsingVariables(nums, target);
        System.out.println(Arrays.toString(result1));
    }

    /*
    Approach:
        1. As array is sorted, use can use Binary Search to find j = (target-x) for number x.
        2. Since we need to do it for all n elements, Complexity: O(n log n)
    */
    public int[] twoSum2_BinarySearch(int[] nums, int target) {
        for(int i=0; i < nums.length; i++){
            int x = nums[i];
            int j = binarySearch(nums, target - x, i + 1);		//search (target-x) and search from i+1 to n
            if(j != -1){
                return new int[] {i, j};
            }
        }
        return null;
    }


    private int binarySearch(int[] nums, int key, int start) {
        int L = start;
        int R = nums.length;
        while(L < R){
            int M = (L+R)/2;
            if(nums[M] < key){
                L = M+1;
            }else{
                R = M;
            }
        }
        return (L == R && nums[L] == key) ? L : -1;
    }

    /*
    	Approach:
			1. Keep two variables: i, j : start i from 0 and j from end
			2.  If A[i]+A[j] > target then j--; i.e decrease j as sum is too big
				If A[i]+A[j] < target then i++; i.e increase i as sum is too small
				If A[i]+A[j] == target then return {i,j}
			Complexity: O(n)
    */
    public int[] twoSum2_UsingVariables(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        while(i < j){
            if(nums[i] + nums[j] > target){
                j--;
            }else if(nums[i] + nums[j] < target){
                i++;
            }else{
                return new int[] {i, j};
            }
        }
        return null;
    }
}