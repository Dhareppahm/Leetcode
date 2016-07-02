package leetcode.medium;

import java.util.Arrays;
import java.util.InputMismatchException;
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

 Time Complexity: O(n) where n = length of input array
 Space Complexity: O(m) to store result
 */
public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges obj = new MissingRanges();
        int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 110, 150, 180};
                //{0,1,3,50,75, 120, 150, 192, 199};
                //{};
                //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
                //{0,1,3,50,75};

        //Make code flexible to handle arbitrary range [start, end]
        int start = 0;
        int end = 199;

        List<String> result = obj.getMissingRanges(input, start, end);
        System.out.println(result.toString());
    }

    public List<String> getMissingRanges(int[] input, int start, int end) {
        List<String> result = new LinkedList<String>();

        //If array is empty, return ["0->99"]
        if(input.length == 0){
            result.add(start + "->"+ end);
            return result;
        }

        int j = 0;                                  //j points to prev number
        for(int i = 1; i <= input.length; i++){
            int low, high, diff = 0;

            if(i == input.length){
                low = input[j] + 1;
                high = end;
                diff = end - input[j];
            }else{
                low = input[j]+1;
                high = input[i]-1;
                diff = input[i] - input[j];
            }

            if(diff > 1){
                if(low == high){
                    result.add(Integer.toString(low));
                } else {
                    result.add(low + "->"+high);
                }
            }
            j = i;                                  //set j to i, before i moves to next number
        }

        return result;
    }
}
