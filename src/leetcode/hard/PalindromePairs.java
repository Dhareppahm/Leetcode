package leetcode.hard;

import java.util.*;

/**
 Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation
 of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]

 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 -----------------------------------------------------------------------------------------------------------
 Understand the problem:
 The brute-force solution to this problem is very simple. For each word, we search all the others and see if it can
 form a palindrome.
 Assume that the ave. length of each word is m and there are totally n words in the list,
 the time complexity would be O(n^2 * m).

 Optimal Solution:
 1. Put all the input string into a Map and the indices of the word

 2. For each word, get all its prefixes, If the prefix is in the map AND the rest of the string is a palindrome,
 then we can find a pair where the first half is the current word, and the second half is the reversed order of prefix.

 3. For each word, get all its postfixes. If the postfix is in the map AND the first half of the string is palindrome,
 then we can find a pair where the reversed order of the postfix is the first part, and the word is the second part of
 the pair.

 The reason why we need to track the position of each word is to avoid the situation like ["c"],
 i.e. the word itself is a palindrome. Then we may mistakely concatenate a "cc" as a palindrome.
 So we can concatenate two words IFF
 1. The key in the map is different from the current word
 2. If they are the same, they must have different indices.

 Time complexity O(m * n ^ 2) where m is the length of the list and the n is the length of the word.
 */

public class PalindromePairs {

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> results = pp.palindromePairs(words);

        System.out.println(Arrays.toString(results.toArray()));
    }

    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> results = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i < words.length; i++) {             //populate map for quick access
            map.put(words[i], i);
        }

        for(int i=0; i < words.length; i++){
            String s = words[i];

            //First check: If s itself is palindrome, if yes then check "" exists, so can add those as pairs
            if(isPalindrome(s)){
                if(map.containsKey("") && map.get("") != i){               //avoid taking itself from map
                    results.add(Arrays.asList(i, map.get("")));
                    results.add(Arrays.asList(map.get(""), i));
                }
            }

            //Then check if exact reverse of s exists i.e palindrome exists, so can add those as pairs
            String reverse = new StringBuilder(s).reverse().toString();
            if(map.containsKey(reverse) && map.get(reverse) != i){              //exist & avoid itself
                results.add(Arrays.asList(i, map.get(reverse)));
            }

            //Now check if substrings of s are palindrome and check if its reverse is present, if yes then its a pair
            for(int j=1; j < s.length(); j++){

                String left = words[i].substring(0, j);
                String right = words[i].substring(j);

                System.out.println("left: "+ right + " right: "+ right);

                //If left part is palindrome, find reversed right part
                if(isPalindrome(left)){
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    if(map.containsKey(reversedRight) && map.get(reversedRight) != i){      //exists & avoid itself
                        results.add(Arrays.asList(map.get(reversedRight), i));
                    }
                }

                //If right part is palindrome, find reversed left part
                if(isPalindrome(right)){
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    if(map.containsKey(reversedLeft) && map.get(reversedLeft) != i){     //avoid itself
                        results.add(Arrays.asList(i, map.get(reversedLeft)));
                    }
                }
            }
        }

        return results;
    }

    private boolean isPalindrome(String s){
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
