package leetcode.easy;

/** 28. Implement strStr()
 * https://leetcode.com/problems/implement-strstr/
 *
 * Implement strStr().
   Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Other Advanced Algorithms for String searching:
 1. KMP Algorithm : O(m+n)
 2. Rabin-Karp algorithm
 3. Boyer-Moore algorithm
 4. Z algorithm
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr obj = new StrStr();
        String haystack = "aczefghijklmnefghijpqrs";
        String needle = "efghijl";

        int index = obj.strStr(haystack, needle);
        System.out.println(index);
    }

    /**
     * Complexity: O(mn) but efficient as inner loop of needle only proceeds when first character of both match,
     * else break inner loop
     */
    public int strStr(String haystack, String needle) {
        for(int i=0; ;i++){
            for(int j=0; ;j++){
                if(j == needle.length())                //end of needle, so return i from where needle match started
                    return i;
                if(i + j == haystack.length())          //return -1 as reached end of haystack
                    return -1;
                if(needle.charAt(j) != haystack.charAt(i+j))    //if 1st char not match, break inner loop else will auto continue
                    break;
            }
        }
    }
}
