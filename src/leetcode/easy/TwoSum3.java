package leetcode.easy;

import java.util.*;

/*
	170	Two Sum III - Data structure design

Question:
Design and implement a TwoSum class. It should support the following operations: add and find.
add(input) – Add the number input to an internal data structure.
find(value) – Find if there exists any pair of numbers which sum is equal to the value.
For example:
add(1); add(3); add(5); find(4) => true; find(7) => false
*/
//------------------------------------------------------------------------------------------------

/** DESIGN 1
* In this design the find operation will take O(n) time and O(n) space. Usually find operations are more so it
* will be slow. What if we store numbers in HashMap?
*/
/*
public class TwoSum3{
	private List<Integer> list;
	public TwoSum3(){
		list = new ArrayList<Integer>();
	}

	private void add(int num){
		list.add(num);
	}
	private boolean find(int target){
		Map<Integer,Integer> map = new HashMap<>();
		for(int num : list){
			if(map.containsKey(num)){
				return true;
			}else{
				map.put(target-num, num);
			}
		}
		return false;
	}

	public static void main(String[] args){
		TwoSum3 obj = new TwoSum3();
		obj.add(1);
		obj.add(3);
		obj.add(5);
		boolean isPresent = obj.find(4);
		System.out.println(isPresent);
		isPresent = obj.find(7);
		System.out.println(isPresent);
	}
}
*/


/** DESIGN 2:
	Store the numbers in HashMap, so find runs in O(1) time
    1. Add: Store <num, freq> in map
    2.Find: Check if difference is present as key in map => If yes, return true
            Check if difference is equal to num and if frequency is 2 => If Yes, return true
            Else return false
*/
public class TwoSum3 {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    private void add(int num){
        if(map.containsKey(num)){
            int freq = map.get(num);
            map.put(num, freq+1);
        }else{
            map.put(num, 1);
        }
    }

    private boolean find(int target){
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int difference = target - num;

            if(map.containsKey(difference)){
                return true;
            }else if(num == difference && entry.getValue() == 2){
                // For duplicates, ensure there are at least two individual numbers.
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args){
        TwoSum3 obj = new TwoSum3();

        obj.add(1);
        obj.add(3);
        obj.add(5);

        boolean isPresent = obj.find(4);
        System.out.println(isPresent);

        isPresent = obj.find(7);
        System.out.println(isPresent);
    }
}
