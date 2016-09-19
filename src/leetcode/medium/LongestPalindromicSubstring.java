package leetcode.medium;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring
 * ---------------------------------------------------------------------------------------------------------------------
 * Approach:
  1. Dynamic Programming:
        Time complexity O(n^2) and Space Complexity O(n^2)
        https://www.youtube.com/watch?v=obBdxeCx_Qs

     DP Formula:
         The base cases are:

         P[ i, i ] ← true                       // For length 1
         P[ i, i+1 ] ← if (Si == Si+1)        // For length 2

        /*Check 2 things:
          The first and last characters should be match
          Rest of the substring should be a match/

        P[ i, j ] ← ( P[i+1, j-1] and Si = Sj ) //For length 3 to n

  2. Expanding around centers: There are 2n-1 centers
        Time Complexity O(n^2) and O(1) space complexity
     a. Keep 2 pointers: start and end, which are start and end index of palindrome substring
     b. Expand around each center (i,i) and (i,i+1) and check palindrome length around that center
     c. Update start and end pointers if new max length is greater.
     d. Return final longest palindromic substring

 3. Manacher Algorithm: Time complexity O(n), O(n) space complexity

 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();

        String input = "ababac";//"abaxabaxabb"; //"ababac";
//                "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";


        //1. Using DP
        System.out.println("#Using Dynamic Programming: ");
        String result = obj.longestPalindromeDP(input);
        System.out.println(result);

        //2. Using Expanding around centers
        System.out.println("\n#Using Expanding around centers approach: ");
        result = obj.longestPalindromeExpandingCenters(input);
        System.out.println(result);

        //3. Manacher
        System.out.println("\n#Using Manachers Algorithm: ");
        int maxLen = obj.longestPalindromicSubstring_Manacher(input.toCharArray());
        System.out.println(maxLen);
    }

    /** 1. Dynamic Programming: Time complexity O(n^2) and Space Complexity O(n^2)
     * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
     * https://www.youtube.com/watch?v=obBdxeCx_Qs
     *
     First initialize the one and two letters palindromes, and work our way up finding all three letters palindromes, and so on…
     Here we are just filling Top Half of matrix T[][]
     */
    public String longestPalindromeDP(String s) {
        int n = s.length();
        int longestPalindromeBeginsAt = 0;      //index where palindrome begins
        int maxLen = 1;                    //length of longest palindrome is atleast 1 as single letter is palindrome of itself
        boolean[][] T = new boolean[n][n];

        //Trivial Case: Check single letter palindromes
        for (int i = 0; i < n; i++) {
            T[i][i] = true;
        }

        //Finding palindromes of 2 characters
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                T[i][i + 1] = true;
                longestPalindromeBeginsAt = i;
                maxLen = 2;
            }
        }

        //Finding palindromes of length 3 to n and saving the longest palindrome
        for (int currLen = 3; currLen <= n; currLen++) {

            for (int i = 0; i < n - currLen + 1; i++) {
                int j = i + currLen - 1;
                if (s.charAt(i) == s.charAt(j)  //1. The first and last characters should be match
                        && T[i + 1][j - 1]) {   //2. Rest of the substring should be a match (use of T: memoization)
                    T[i][j] = true;
                    longestPalindromeBeginsAt = i;
                    maxLen = currLen;
                }
            }
        }

//        System.out.println("Length of Longest Substring : "+maxLen + " : " + s.length() + " : "+longestPalindromeBeginsAt);
        return s.substring(longestPalindromeBeginsAt, longestPalindromeBeginsAt + maxLen);

    }


    /** 2. Expanding around 2n-1 centers.
     eg: abccba, abcba
     */
    public String longestPalindromeExpandingCenters(String s) {
        int start = 0, end = 0;                                     //start and end pointers to longest palindrome substring
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);                 //Expand around center
            int len2 = expandAroundCenter(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if (maxLen > end - start) {                             //if maxLen is greater than previous end - start update them
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {    //move left towards left and right towards right
            L--;                                                            //if palindrome i.e characters are same
            R++;
        }
        return R - L - 1;
    }


    /** https://www.youtube.com/watch?v=V-sEwsca1ak
 * Linear time Manacher's algorithm to find longest palindromic substring.
 *
 * There are 4 cases to handle
 * Case 1 : Right side palindrome is totally contained under current palindrome. In this case do not consider this as center.
 * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case.
 *          No better palindrome will be found on right.
 * Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is proper prefix of current palindrome.
 *          Make largest such point as next center.
 * Case 4 : Right side palindrome is proper suffix but its left corresponding palindrome is be beyond current palindrome.
 *          Do not consider this as center because it will not extend at all.
 *
 * To handle even size palindromes replace input string with one containing $ between every input character and in start and end.
     */
    public int longestPalindromicSubstring_Manacher(char input[]) {
        int index = 0;

        //preprocess the input to convert it into type abc -> $a$b$c$ to handle even length case.
        //Total size will be 2*n + 1 of this new array.
        char newInput[] = new char[2*input.length + 1];
        for(int i=0; i < newInput.length; i++) {
            if(i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        //create temporary array for holding largest palindrome at every point. There are 2*n + 1 such points.
        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;
        //here i is the center.
        for(int i=0; i < newInput.length; ) {
            //expand around i. See how far we can go.
            while(start >0 && end < newInput.length-1 && newInput[start-1] == newInput[end+1]) {
                start--;
                end++;
            }
            //set the longest value of palindrome around center i at T[i]
            T[i] = end - start + 1;

            //this is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out of loop.
            if(end == T.length -1) {
                break;
            }
            //Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number input.
            int newCenter = end + (i%2 ==0 ? 1 : 0);

            for(int j = i + 1; j <= end; j++) {

                //i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome. So take minimum
                //of either left side palindrome or distance of j to end.
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                //Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case 1 or case 4
                //As soon as we find a center lets break out of this inner for loop.
                if(j + T[i - (j - i)]/2 == end) {
                    newCenter = j;
                    break;
                }
            }
            //make i as newCenter. Set right and left to atleast the value we already know should be matching based of left side palindrome.
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }


        //find the max palindrome in T and return it.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < T.length; i++) {
            int val;

            val = T[i]/2;
            if(max < val) {
                max = val;
            }
        }
        return max;
    }


}
