package leetcode.medium;

/**
 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

 153. Find Minimum in Rotated Sorted Array
 154. Find Minimum in Rotated Sorted Array II

 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 You may assume no duplicate exists in the array.
 -----------------------------------------------------------------------------------------------------------------------
Approach:
    1. Modified binary search, as array is rotated around pivot, we also check if A[L] >= A[R]
    2. Check if A[M] > A[R], if yes, then Minimum must be between {M+1, R} else {L, M}
    3. Return A[L] which will be smallest number
    4. Return A[L] which will be smallest number
    5. Complexity is: O(log n)

 Note: For duplicates, we cannot just discard the left or right array, if there is a case like: A[L] == A[M] == A[R]
 So in this case, we just have to increment L, and repeat binary search process.
 So, in worst case the complexity will be : O(n)

 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {

        FindMinimumInRotatedSortedArray obj = new FindMinimumInRotatedSortedArray();
        int[] nums = //{1,1,1,1,1,0,1};
                    {4,5,6,7,0,1,2};
        int min = obj.findMin_WithoutDuplicates(nums);
        System.out.println("Minimum without duplicates = "+ min);

        min = obj.findMin_WithDuplicates(nums);
        System.out.println("Minimum with duplicates = "+ min);
    }

    /**
     * Input array has NO DUPLICATES
     * Complexity is: O(log n)
     */
    public int findMin_WithoutDuplicates(int[] nums){
        int L = 0, R = nums.length - 1;

        while(L < R && nums[L] >= nums[R]){             //2nd condition: as its rotated, A[L] >= A[R]
            int M = (L + R)/2;
            if(nums[M] > nums[R]){
                L = M + 1;
            }else{
                R = M;
            }
        }
        return nums[L];
    }

    /**
     * Input array CONTAINS DUPLICATES
     *
     * Best case Complexity: O(log n)
     * Worst case Complexity: O(n)
     */
    public int findMin_WithDuplicates(int[] nums){
        int L = 0, R = nums.length - 1;

        while(L < R && nums[L] >= nums[R]){             //2nd condition: as its rotated, A[L] >= A[R]
            int M = (L + R)/2;
            if(nums[M] > nums[R]){
                L = M + 1;
            }else if(nums[M] < nums[R]){
                R = M;
            }else{                                      //A[L] == A[M] == A[R]
                L = L + 1;                              //Like linear search, move forward to in array to find min
            }
        }
        return nums[L];
    }
}
