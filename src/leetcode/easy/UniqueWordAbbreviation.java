package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 https://leetcode.com/problems/unique-word-abbreviation/
 288. Unique Word Abbreviation

 Approach:
 1. Get abbrevation key using a sample utility method.
 2. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation. So check both: key not present || if its same word, then we should return true
 3. Also as per sample test cases,
 If there is more than one string belong to the same key then the key will be invalid,
 and we set the value to ""  (invalid)
 and return false for both such words as per sample test cases
 */
public class UniqueWordAbbreviation {

    Map<String, String> map = map = new HashMap<>();

    public static void main(String[] args) {
        String[] dictionary = {"it", "door", "cake", "card", "hello"};
        UniqueWordAbbreviation vwa = new UniqueWordAbbreviation(dictionary);
        System.out.println(vwa.isUnique("dear"));
        System.out.println(vwa.isUnique("cart"));
        System.out.println(vwa.isUnique("cane"));
        System.out.println(vwa.isUnique("make"));
        System.out.println(vwa.isUnique("it"));
        System.out.println(vwa.isUnique("hello"));
    }

    public UniqueWordAbbreviation(String[] dictionary) {
        for(String s : dictionary){
            String key = getAbbreviation(s);
            // If there is more than one string belong to the same key then the key will be invalid,
            // we set the value to "" and return false for both such words as per sample test cases
            if(map.containsKey(key)){
                if(!map.get(key).equals(s)) {
                    map.put(key, "");
                }
            } else {
                map.put(key, s);
            }
        }
    }

    public boolean isUnique(String word) {
        System.out.println(map);
        String key = getAbbreviation(word);

        //A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
        // So check both: key not present || if its same word
        return !map.containsKey(key) || map.get(key).equals(word);
    }

    private String getAbbreviation(String s){
        int len = s.length();
        if(len <= 2){
            return s;
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append(""+s.charAt(0) + s.substring(1, len-1).length() + ""+s.charAt(len-1));
            return sb.toString();
        }
    }
}

