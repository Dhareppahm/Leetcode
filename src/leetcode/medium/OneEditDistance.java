package leetcode.medium;

/**
 * https://oj.leetcode.com/problems/one-edit-distance/
 *
 * Question:
 Given two strings S and T, determine if they are both one edit distance apart.
 Hint:
 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart.
 2. It might help if you consider these cases separately, m == n and m ≠ n.
 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements. If m > n, we could just simply swap S and T.
 4. If m == n, it becomes finding if there is exactly one modified operation.
    If m ≠ n, you do not have to consider the delete operation. Just consider the insert operation in T.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 1. This is special case of general Edit distance problem which is solved by DP in O(mn) time and O(mn) space
 2. As we have to check only 1-edit distance, we can loop once concurrently and check if Insert/Modify/Delete have to be done for 1 char
 3. Assume m <= n, else just swap S and T
 4. Handle - Modify, Delete/Insert, Append operations correctly - all cases
 */
public class OneEditDistance {
    public static void main(String[] args) {
        OneEditDistance oed = new OneEditDistance();

        String s = "abcde";
        String t = "abcXe";
        boolean flag = oed.isOneEditDistance(s, t);
        System.out.println("S = "+ s);
        System.out.println("T = "+ t);
        System.out.println(flag);
    }

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        //Swap if m > n as we assume m <= n
        if(m > n){
            return isOneEditDistance(t, s);
        }

        //Return immediately is (n-m) > 1
        if(n - m > 1){
            return false;
        }

        int i=0;
        while(i < m && s.charAt(i) == t.charAt(i)){         //Proceed till characters in both string match
            i++;
        }

        int extraChars = n - m;                         //Number of extra chars
        if(i == m){                                     //Check end reached if both same
            return extraChars == 1;                      //If only 1 extra char remains at end, return true
        }                                               //If both strings are same, return false

        //Modify Opr: If extra char in middle, move forward
        if(extraChars == 0){
            i++;
        }

        while (i < m && s.charAt(i) == t.charAt(i + extraChars)){   //Insert/Delete Opr: Proceed skipping 1 extra character
            i++;
        }

        return i == m;                                  //Return true only after we reach end if s
    }

}
