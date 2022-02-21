package leetcode.easy;

/**
 https://leetcode.com/problems/binary-search/

 Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums.
 If target exists, then return its index. Otherwise, return -1.

 You must write an algorithm with O(log n) runtime complexity.

 Example 1:
 Input: nums = [-1,0,3,5,9,12], target = 9
 Output: 4
 Explanation: 9 exists in nums and its index is 4
 Example 2:

 Input: nums = [-1,0,3,5,9,12], target = 2
 Output: -1
 Explanation: 2 does not exist in nums so return -1
 */
public class _704_BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        _704_BinarySearch obj = new _704_BinarySearch();
        System.out.println(obj.search(nums, target));
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            //int mid = (left + right)/2;               //Overflow error
            int mid = left + ((right - left) / 2);         //Avoids overflow
            if (nums[mid] < target) {              //target in right half
                left = mid + 1;
            } else if (nums[mid] == target) {        //target found
                return mid;
            } else {                              //search in left half
                right = mid - 1;
            }
        }
        return -1;
    }
}
