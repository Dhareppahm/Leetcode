package leetcode.easy;

import java.util.*;


/*
349. Intersection of Two Arrays
https://leetcode.com/problems/intersection-of-two-arrays/
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

*/
public class IntersectionTwoArrays{

	public static void main(String[] args){
		IntersectionTwoArrays i2a = new IntersectionTwoArrays();
		int[] nums1 = {1, 2, 2, 1};
		int[] nums2 = {2, 2};

		int[] result = i2a.intersection(nums1, nums2);
		System.out.println(Arrays.toString(result));

	}

	/*
	Create Result hashSet. Put nums2 in hashset and check nums1 if number present in set.
	If present then add in result hashset
	*/
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> result = new HashSet<Integer>();
		Set<Integer> nums2Set = new HashSet<Integer>();

		for(int num: nums2){                //Put nums2 in hashset
			nums2Set.add(num);
		}

		for(int num : nums1){               //check nums1 if number present in set
			if(nums2Set.contains(num)){
				result.add(num);            //If present then add in result hashset
			}
		}

		int[] res = new int[result.size()];
		int j=0;
		for(int num : result){
			res[j++] = num;
		}
		return res;
	}
}