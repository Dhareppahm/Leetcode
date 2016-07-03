package leetcode.hard;

/**
 * Longest Substring With Atmost Two Distinct Characters
 * https://oj.leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

 Question:
 Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 For example,
 Given S = “eceba”,
 T is "ece" which its length is 3.
 ----------------------------------------------------------------------------------------------------------------
 Approach:
    1. Method iterates n times and therefore its runtime complexity is O(n). We use three pointers: i, j, and k.

    2. Generalized: The key is when we adjust the sliding window to satisfy the invariant, we need a counter of the
                    number of times each character appears in the substring.
 */
public class LongestSubstringWithAtmostTwoDistinctCharacters {
    public static void main(String[] args) {
        LongestSubstringWithAtmostTwoDistinctCharacters obj = new LongestSubstringWithAtmostTwoDistinctCharacters();
        String s = "abbaacccccddd";
        int maxLen = obj.lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(maxLen);

        int k = 2;
        maxLen = obj.lengthOfLongestSubstringWithKDistinct_Generalised(s, k);
        System.out.println("Generalized for K distinct = "+maxLen);
    }

    public int lengthOfLongestSubstringWithKDistinct_Generalised(String s, int k) {
        int[] counts = new int[256];
        int maxLen = 0, distinctNumbers = 0;
        int i = 0;
        for(int j = 0; j < s.length(); j++){
            if(counts[s.charAt(j)] == 0){
                distinctNumbers++;
            }
            counts[s.charAt(j)]++;                  //increment frequency of char at j

            while(distinctNumbers > k){            //here k=2 => check till distinct char <= k (2)
                counts[s.charAt(i)]--;             //decrement frequency of char at i
                if(counts[s.charAt(i)] == 0){      // i points to previous k distinct character
                    distinctNumbers--;
                }
                i++;
            }
            maxLen = Math.max(j-i+1, maxLen);       //choose max of (previous maxLen) , current (j - i +1)
        }
        return maxLen;
    }


    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxLen = 0, i = 0, j = -1;
        for(int k=1; k < s.length(); k++){
            if(s.charAt(k) == s.charAt(k-1)){       //proceed if current and prev characters are same
                continue;
            }

            if(j >= 0 && s.charAt(j) != s.charAt(k)){   //i pointer is updated to j's next value when j and k values are different.
                maxLen = Math.max(k - i, maxLen);
                i = j + 1;
            }
            j = k - 1;
        }
        maxLen = Math.max(s.length() - i, maxLen);
        return maxLen;
    }


}
