package leetcode.medium;

/**
 48. Search Insert Position
 https://leetcode.com/problems/search-insert-position/
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it
 would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
------------------------------------------------------------------------------------------------------------------------
Approach:
    1. Use binary search, as usual if Target found, return index of number
    2. When the while loop ends, L must be equal to R and it is a valid index.
    3. Obviously, if A[L] is equal to target, we return L.
    4. If A[L] is greater than target, that means we are inserting target before A[L], so we return L.
    5. *** If A[L] is less than target, that means we insert target after A[L], so we return L + 1
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();
        int[] nums = {1,3,5,6};
        int target = 9;
        int position = sip.searchInsert(nums, target);
        System.out.println(position);
    }

    public int searchInsert(int[] nums, int target) {
        int L = 0 , R = nums.length - 1;
        while(L < R){
            int M = (L + R)/2;
            if(nums[M] < target){
                L = M + 1;
            }else{
                R = M;
            }
        }
//        System.out.println("L = "+ L + " R = "+ R);
        return (target > nums[L]) ? L+1 : L;
    }
}
