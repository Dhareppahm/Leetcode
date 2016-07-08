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

                    Maintain distinctCharacters counter. If it becomes more than K, move window ahead and check
                    Keep track of window using i, j. Keep track of max window => maxLen, maxStart (i), maxEnd(j)
 */
public class LongestSubstringWithAtmostTwoDistinctCharacters {
    public static void main(String[] args) {
        LongestSubstringWithAtmostTwoDistinctCharacters obj = new LongestSubstringWithAtmostTwoDistinctCharacters();
        String s = "abbaacccccdddd";

//        int maxLen = obj.lengthOfLongestSubstringTwoDistinct(s);
//        System.out.println(maxLen);
        int k = 3;
        int maxLen = obj.lengthOfLongestSubstringWithKDistinct_Generalised(s, k);
        System.out.println("Generalized for K distinct = "+maxLen);
    }


    //Longest Substring with ATMOST k distinct characters
    public int lengthOfLongestSubstringWithKDistinct_Generalised(String s, int k) {
        if(s == null || s.length() == 0){
            System.out.println("Empty string found");
            return 0;
        }

        //Logic
        int[] counts = new int[256];
        int maxLen = 0, distinctChars = 0, maxStart = 0, maxEnd = 0;
        int i = 0;                                  //i and j are window start, end pointers
        for(int j = 0; j < s.length(); j++) {
            if(counts[s.charAt(j)] == 0){           //only when we first encounter character, increment distinct char count
                distinctChars++;
            }
            counts[s.charAt(j)]++;                  //increment frequency of char at j

            while(distinctChars > k) {             //CORE LOGIC: here k=2 => check till distinct char <= k (2)
                counts[s.charAt(i)]--;             //decrement frequency of char at i
                if(counts[s.charAt(i)] == 0) {     // i points to previous distinct character, so we loop till distinct char <= k
                    distinctChars--;
                }
                i++;                                //move window start pointer ahead
            }

            //update maxLen and start,end to get max substring
            if(j - i + 1 > maxLen){
                maxLen = j-i+1;
                maxStart = i;
                maxEnd = j;
            }
        }

        System.out.println(s.substring(maxStart, maxEnd+1));
        return maxLen;
    }

    /*public int lengthOfLongestSubstringTwoDistinct(String s) {
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
    }*/

}
