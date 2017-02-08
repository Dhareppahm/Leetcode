package leetcode.hard;

/**
 4. Median of Two Sorted Arrays
 https://leetcode.com/problems/median-of-two-sorted-arrays/
 There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5
 ----------------------------------------------------------------------------------------------------------------
 Approach:
 https://discuss.leetcode.com/topic/16797/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation

 "if we cut the sorted array to two halves of EQUAL LENGTHS, then median is the AVERAGE OF Max(lower_half) and Min(upper_half)
 i.e. the two numbers immediately next to the cut".

 Let L and R be numbers representing numbers next to Left and right cut respectively
 L = (mid-1)/2  & R = mid/2

 and we need to make sure - any number in lower halves <= any number in upper halves.
 We can do this by making sure: L1 <= R2 && L2 <= R1

 If L1 > R2 then low = mid+1
 Else If L2 > R1 then high = mid-1
 Else median = MAX(L1,L2) + MIN(R1, R2)/2

 Repeat till we find the right cut and satisfy median condition.

 */
public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        MedianTwoSortedArrays obj = new MedianTwoSortedArrays();
        int[] num1 = {1,2,3,4};
        int[] num2 = {4,5,6,7,8,10};
        double median = obj.findMedianSortedArrays(num1,num2);
        System.out.println(median);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if(n2 > n1){
            return findMedianSortedArrays(nums2, nums1);       //assume n1 > n2, else swap
        }

        int low = 0;
        int high = n2*2;        //minimum length is n2 as per assumption
        while(low <= high) {                 //Recursively do binary search to find median
            int mid2 = (low + high)/2;      //Try cut 2 in smaller array
            int mid1 = n1+n2 - mid2;        //Calculate cut 1 accordingly

            double L1 = (mid1==0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];  //L = (mid-1)/2  & R = mid/2, also check edge case
            double L2 = (mid2==0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1==n1*2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2==n2*2) ? Integer.MAX_VALUE : nums2[(mid2)/2];

            //Now check - any number in lower halves <= any number in upper halves.
            //i.e We only have to check L1 <= R2 && L2 <= R1

            if (L1 > R2){
                low = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            } else if (L2 > R1) {
                high = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            } else {
                return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, its the right cut.
            }
        }
        return -1;
    }
}
