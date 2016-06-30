package leetcode.hard;

import java.util.HashSet;

/**
https://leetcode.com/problems/longest-consecutive-sequence/

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 -------------------------------------------------------------------------------------------------------------------
Approach:
    1. Naive solution:
        Sort array in increasing order and check length till next elements are greater than prev. Return length.
    2. Linear Solution:
        a) We use the O(1) access time property of HashSet / HashMap
        b) Hash all the array elements
        c) Check if current element is the starting element of a sequence. i.e check num-1 is not present, if not present
           then num is starting element.
        d) If num is starting element in sequence then only check for next elements in the sequence, and update maxLength
           if this length is more
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] numbers = {100,4,200,1,2,3};
		
		int len = findLongestConseqSubseq(numbers);
		System.out.println("Longest Subsequence length: "+len);

	}

	// Returns length of the longest consecutive subsequence
    static int findLongestConseqSubseq(int arr[]) {
        HashSet<Integer> set = new HashSet<Integer>();
        int maxLength = 0;
 
        // Hash all the array elements
        for (int i=0; i < arr.length; ++i)
            set.add(arr[i]);
 
        // check each possible sequence from the start then update optimal maxLength
        for (int i=0; i < arr.length; ++i) {
            // check if current element is the starting element of a sequence. 
        	//i.e check num-1 is not present, if not present then num is starting element
        	int num = arr[i];
            if (!set.contains(num - 1)) {
            	
                //If num is starting element in sequence then only check for next elements in the sequence
                int j = num;
                while (set.contains(j)){
                    j++;
                }
 
                // update optimal maxLength if this length is more
                if (maxLength < j - num){
                    maxLength = j - num;
                }
                
            }
        }
        return maxLength;
    }
}
