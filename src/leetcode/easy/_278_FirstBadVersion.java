package leetcode.easy;

/**
 https://leetcode.com/problems/first-bad-version/

 You are a product manager and currently leading a team to develop a new product.
 Unfortunately, the latest version of your product fails the quality check.
 Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following
 ones to be bad.

 You are given an API bool isBadVersion(version) which returns whether version is bad.
 Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Example 1:

 Input: n = 5, bad = 4
 Output: 4
 Explanation:
 call isBadVersion(3) -> false
 call isBadVersion(5) -> true
 call isBadVersion(4) -> true
 Then 4 is the first bad version.

 Example 2:

 Input: n = 1, bad = 1
 Output: 1
 */
public class _278_FirstBadVersion {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        _278_FirstBadVersion obj = new _278_FirstBadVersion();
        System.out.println(obj.firstBadVersion(nums.length));
    }

    /* The isBadVersion API is defined in the parent class VersionControl. boolean isBadVersion(int version); */
    private boolean isBadVersion(int version) {
        return version == 4;
    }

    public int firstBadVersion(int n) {
        int left = 0, right = n, result = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (!isBadVersion(mid)) {       //False => bad version to right
                left = mid + 1;
            } else if (isBadVersion(mid)) { //one of bad version found - change right to search before
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }
}
