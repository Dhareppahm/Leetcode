package leetcode.hard;

/**
 https://leetcode.com/problems/regular-expression-matching/
 https://www.youtube.com/watch?v=l3hda49XcDE
 10. Regular Expression Matching

 Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true
 -----------------------------------------------------------------------------------------------------------------------
 Dynamic Programming:
 Time Complexity: O(m*n), Space Complexity: O(m*n)

 https://www.youtube.com/watch?v=l3hda49XcDE

DP Formula:
            (For '.') T[i-1][j-1] if pattern[j-1]=='.' || text[i]==pattern[j]
 T[i][j] =  (For '*') check two things
            1. T[i][j-2] - check value 2 columns back, for 0 or more occurances
            2. T[i-1][j] - check value above, if pattern[i]=='.' || text[i]==pattern[j]

 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();

        boolean[][] T = new boolean[text.length+1][pattern.length+1];       //size is +1 of text and pattern length
        T[0][0] = true;                                 //empty text and empty pattern are match - Trivial

        //For patterns: a*, a*b*, a*b*c* - empty text can also be match
        for(int i=1; i < T[0].length; i++){
            if(pattern[i-1] == '*'){
                T[0][i] = T[0][i-2];        //check 0 occurences by going 2 columns before
            }
        }

        //Core Logic
        for(int i=1; i < T.length; i++){
            for(int j=1; j < T[0].length; j++){
                if(pattern[j-1] == '.' || text[i-1] == pattern[j-1]){
                    T[i][j] = T[i-1][j-1];
                }else if(pattern[j-1] == '*'){
                    T[i][j] = T[i][j-2];        //1. First check 0 or more occurances
                    if(pattern[j-2] == '.' || pattern[j-2] == text[i-1]){   //2. Then if . or char match check above value
                        T[i][j] = T[i][j] | T[i-1][j];                  //Do or with 1 and 2
                    }
                }else{
                    T[i][j] = false;
                }
            }
        }

        return T[text.length][pattern.length];
    }

    public static void main(String[] args) {
        RegularExpressionMatching obj = new RegularExpressionMatching();

        boolean flag = obj.isMatch("aa", "aa");
        System.out.println("isMatch(\"aa\", \"aa\") = " + flag);

        flag = obj.isMatch("aa", "a");
        System.out.println("isMatch(\"aa\", \"a\") = " + flag);

        flag = obj.isMatch("aa", "a.");
        System.out.println("isMatch(\"aa\", \"a.\") = "+ flag);

        flag = obj.isMatch("aab", "c*a*b");
        System.out.println("isMatch(\"aab\", \"c*a*b\") = "+ flag);

    }
}
