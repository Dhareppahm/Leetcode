package leetcode.easy;

/**
 https://leetcode.com/problems/longest-common-prefix/
 14. Longest Common Prefix

 Write a function to find the longest common prefix string amongst an array of strings
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        String[] strs = {"aabcd","aab","aabc"};
        String result = lcp.longestCommonPrefix(strs);
        System.out.println(result);

        result = lcp.longestCommonPrefix2(strs);
        System.out.println(result);
    }

    /**
     * Horizontal Scanning
     Time complexity : O(S) , where S is the sum of all characters in all strings.
     Space complexity : O(1). We only used constant extra space.
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";

        String prefix = strs[0];                //to begin prefix is the first string
        for(int i=1; i < strs.length; i++){
            while(strs[i].indexOf(prefix) != 0){        //Returns index within this string of the first occurrence of substring.
                prefix = prefix.substring(0, prefix.length()-1); //Reduce prefix length by 1 will its becomes same
            }
        }
        return prefix;
    }

    //Vertical Scan Approach - Easy to understand
    public String longestCommonPrefix2(String[] strs) {
        if(strs.length == 0) return "";

        for(int i=0; i < strs[0].length(); i++){
            char c1 = strs[0].charAt(i);            //i-th char in first string

            for(int j=1; j < strs.length; j++){     //i-th char in other strings
//                char c2 = strs[j].charAt(i);
                if(i >= strs[j].length() || c1 != strs[j].charAt(i)){       //if i becomes >= others strings length OR c1 != c2, return
                    return strs[0].substring(0,i);      //return substring till matched
                }
            }
        }
        return strs[0];                             //by default first string is prefix
    }

}
