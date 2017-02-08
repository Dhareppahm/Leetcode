package leetcode.easy;

import java.util.HashMap;

/**
 https://leetcode.com/problems/first-unique-character-in-a-string/
 387. First Unique Character in a String
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 Do 2 passes of the string
    a. In first pass: Populate map with frequency of each character. Can also use array [256]
    b. In second pass: Iterate string and check map and return the first index where value is 1

 */
public class FirstUniqueCharInString {

    public static void main(String[] args) {
        FirstUniqueCharInString obj = new FirstUniqueCharInString();
        String s = "loveleetcode";
        int index = obj.firstUniqChar(s);
        System.out.println(index);
    }

    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();      //can also use int[256]

        // Do 2 passes of the string
        // In first pass: Populate map with frequency of each character
        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                int freq = map.get(ch);
                map.put(ch, freq+1);
            }else{
                map.put(ch, 1);
            }
        }

        //In second pass: Iterate string and check map and return the first index where value is 1
        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.get(ch) == 1){
                return i;
            }
        }
        return -1;
    }
}
