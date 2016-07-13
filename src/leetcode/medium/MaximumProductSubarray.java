package leetcode.medium;

/**
 152. Maximum Product Subarray
 https://leetcode.com/problems/maximum-product-subarray/

 Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 -----------------------------------------------------------------------------------------------------------------------

Approach:
 1. Same as Maximum Sum Subarray except that also keep track of smallest product, as it may become largest if multiplied by -ve no
 2. DP formula should check this also now.

 DP Formula:
 f(k-1)
 g(k-1)
 f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
 g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )

 */
public class MaximumProductSubarray {

    public static void main(String[] args) {
        MaximumProductSubarray mps = new MaximumProductSubarray();
        int[] nums = {-2,-3,-2,-4};
        int maxProduct = mps.maxProduct(nums);
        System.out.println(maxProduct);
    }

    public int maxProduct(int[] A) {
        int maxTillNow = A[0];
        int minTillNow = A[0];            //IMP: keep track of smallest product, as it may become largest if multiplied by -ve no
        int maxProduct = A[0];

        for(int i=1; i < A.length; i++) {
            int prevMax = maxTillNow;
            int prevMin = minTillNow;

            maxTillNow = Math.max(Math.max(prevMax * A[i], A[i]), prevMin * A[i]);
            minTillNow = Math.min(Math.min(prevMin * A[i], A[i]), prevMax * A[i]);

            maxProduct = Math.max(maxTillNow, maxProduct);
        }
        return maxProduct;
    }
}
