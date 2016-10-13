package leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 *
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 ------------------------------------------------------------------------------------------------------------------------------------------------
 APPROACH 1: Sliding Window approach

 A sliding window is an abstract concept commonly used in array/string problems.
 A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j)
 [i,j) (left-closed, right-open). A sliding window is a window "slides" its two boundaries to the certain direction.
 For example, if we slide [i, j)[i,j) to the right by 1 element, then it becomes [i+1, j+1)

 a) Using Set
 Back to our problem. We use HashSet to store the characters in current window [i, j).
 Then we slide the index j to the right. If it is not in the HashSet, we slide j further. Doing so until s[j] is already
 in the HashSet. At this point, we found the maximum size of substrings without duplicate characters start with index i.
 If we do this for all i, we get our answer

 Time complexity (with Hashset): O(2n) as we move i little by little, so traverse each char atmost twice

 b) Using Map
 The above solution requires at most 2n steps. In fact, it could be optimized to require only n steps.
 Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index.
 Then we can skip the characters immediately when we found a repeated character.

 Time complexity: O(n)

 Approach 2: (V.v.v. tricky : Understand this logic and write code)
    1. Lookup if a character exist in substring instantaneously using array to store characters that have appeared
    2. If previously character is not part of Substring (not found before) => update its position and increment length
    3. If it was part of Substring (found before), then our substring changes. It becomes substring
        starting from next character of previous instance to currently scanned character.
    4. Keep track of max length everytime and update if its greater.

 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();

        String input = "abcabcbbefghijbb";//"abcabcbbefghijbb";//"abcabcbb";
        int length = obj.lengthOfLongestSubstring(input);
        System.out.println(length);

        //Using Sliding window technique
        int len = obj.lengthOfLongestSubstringNoRepeat_Set(input);
        System.out.println(len);

        len = obj.lengthOfLongestSubstringNoRepeat_Map(input);
        System.out.println(len);
    }



    //Using Sliding window technique - Set          (Best & Easy to understand)
    //Time complexity (with set): O(2n) as we move i little by little, so traverse each char atmost twice
    private int lengthOfLongestSubstringNoRepeat_Set(String s) {
        Set<Character> set = new HashSet<>();   //HashSet to store the characters in current window [i, j)
        int i = 0, j = 0, n = s.length();
        int max = 0;

        while(i < n && j < n){
            if(!set.contains(s.charAt(j))){     //not present so add in window (set)
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, j-i);       //track max len
            }else{
                set.remove(s.charAt(i));    //abca : will come in this case, i=0, j=3; so remove a from set and i++
                i++;
            }
        }

        return max;
    }

    //Using Map:  Time complexity: O(n)
    private int lengthOfLongestSubstringNoRepeat_Map(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int max = 0;

        int i=0;
        for(int j=0; j < n; j++){
            if(map.containsKey(s.charAt(j))) {          //found char
                i = Math.max(map.get(s.charAt(j)), i);          //jump i to j+1
            }
            map.put(s.charAt(j), j+1);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s){
        int maxLen = 0;
        int[] visited = new int[256];           //stores the position of characters visited. Initialize it with -1
        Arrays.fill(visited, -1);

        int prev = 0;                           //to keep track of prev location of character
        for(int i = 0; i < s.length(); i++){

            if(visited[s.charAt(i)] >= prev){         //If character was previously visited, then substring changes.
                prev = visited[s.charAt(i)] + 1;    //Get prev to previous position + 1. As new substring starts from "next char of previous instance of current char"
            }

            visited[s.charAt(i)] = i;       //update current position
            maxLen = Math.max(i - prev + 1, maxLen);
        }

        //// Extra :: To retrieve longest substring
        /*int temp = maxLen;
        HashSet<Integer> substringIndexes = new HashSet<Integer>();     //store substring result indexes in HashSet to check later

        while(temp > 0){
            int value = 0, index = 0;
            //get max value and its index from visited. Update it to -1 so we don't get same index again
            for(int i = 0; i < visited.length; i++){
                if(visited[i] != -1 && visited[i] > value){
                    value = visited[i];
                    index = i;
                }
            }
            visited[index] = -1;
            substringIndexes.add(index);
            temp--;
        }

        StringBuilder sb = new StringBuilder();
        //Loop through string and check if index present in HashSet & append in sequence found in original string
        for(int i=0; i < s.length(); i++){
            int index = (int) s.charAt(i);
            if(substringIndexes.contains(index)){
                sb.append(s.charAt(i));
                substringIndexes.remove(index);
            }
        }

        System.out.println(sb.toString());*/
        return maxLen;
    }

}
