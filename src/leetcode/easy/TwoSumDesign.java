package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 70. Two Sum III - Data structure design

 https://leetcode.com/problems/two-sum-iii-data-structure-design/
 Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 For example,
 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false
 --------------------------------------------------------------------------
 add – O(1) runtime, find – O(n) runtime, O(n) space – Store input in hash table:
 A simpler approach is to store each input into a hash table with the input as key and its count as value.
 To find if a pair sum exists, just iterate through the hash table in O(n) runtime. Make sure you are able to handle
 duplicates correctly.

 */
public class TwoSumDesign {

    Map<Integer, Integer> map = new HashMap<>();        //Store number and its frequency in map

    // Add the number to an internal data structure.
    public void add(int number) {
        if(map.containsKey(number)){
            int freq = map.get(number);
            map.put(number, freq+1);
        }else{
            map.put(number, 1);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int findNum = value - num;

            if(num == findNum){
                if(entry.getValue() >= 2)       //check if more than 2 occurances present
                    return true;
            }else if(map.containsKey(findNum)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSumDesign twoSum = new TwoSumDesign();
        int[] arr = {2,1,4,5};
        twoSum.add(2);twoSum.add(1);twoSum.add(4);twoSum.add(5);twoSum.add(9);
        System.out.println(twoSum.find(10));
        System.out.println(twoSum.find(12));
    }
}
