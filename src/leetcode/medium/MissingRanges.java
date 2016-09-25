package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Question:
 Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.

 For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

 Example Questions Candidate Might Ask:
 Q: What if the given array is empty?
 A: Then you should return [“0->99”] as those ranges are missing.

 Q: What if the given array contains all elements from the ranges?
 A: Return an empty list, which means no range is missing.
 ----------------------------------------------------------------------------------------------------------------------
 Approach:
    i. List out test cases.
    ii. You should be able to extend the above cases not only for the range [0,99], but any arbitrary range [start, end].

 Understand the problem:
 The problem itself is not hard at all. The key is to handle several corner cases. e.g.
 -- If the array is empty, the missing ranges should be from lower to upper, inclusive.
 -- For the leading missing range, e.g. -2 , [-1], -1. The output should be "-2". Note that the lower bound is inclusive.
 -- For the trailing missing range, e.g. -2, [-2], 1, the output should be "-1->1". The upper bound is inclusive as well.
 
 Approach: Easy but very tricky to handle all cases
 1. Handle edge case of empty array: [0->99]
 2. Handle separately handle first, middle and last entries
     a. Add first entry if nums[0] is greater than lower i.e (nums[0] - lower > 0)
     b. For all middle entries check (nums[i] - nums[i-1] > 1)
     c. For last entry check (upper - nums[len-1] > 0)

 Time Complexity: O(n) where n = length of input array
 Space Complexity: O(m) to store result
 */
public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges obj = new MissingRanges();
        int[] input = //{3, 36, 45, 49,50,79, 98};
                //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 110, 150, 180};
                //{0,1,3,50,75, 120, 150, 192, 199};
                //{};
                //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
                {0,1,3,50,75};

        //Make code flexible to handle arbitrary range [start, end]
        int start = 0;
        int end = 99;

        List<String> result = obj.findMissingRanges(input, start, end);
        System.out.println(result.toString());
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();

        //Edge case: empty input [0->99]
        if(nums == null || nums.length == 0){
            addResult(lower, upper, result);
            return result;
        }

        //Separately handle first, middle and last entries
        if(nums[0] - lower > 0){                            // Add first entry if nums[0] is greater than lower
            addResult(lower, nums[0]-1, result);
        }

        for(int i=1; i < nums.length; i++){                 //For all middle entries
            if(nums[i] - nums[i-1] > 1)
                addResult(nums[i-1] + 1, nums[i] - 1, result);
        }

        if(upper - nums[nums.length-1] > 0){                //Add last entry from nums[n] to upper
            addResult(nums[nums.length-1] + 1, upper, result);
        }

        return result;
    }

    private void addResult(int low, int high, List<String> result){
        if(low == high){
            result.add(Integer.toString(low));
        }else{
            result.add(low + "->"+ high);
        }
    }
}
