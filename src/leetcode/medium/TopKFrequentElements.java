package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 <= k <= number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 Approach:
    1. Naive Approach: Sort array and select top k elements => O(n log n)
    2. Heap Approach:
        Store frequency in HashMap
        Create a Min-Heap, so we can easily compare with root frequency. Store only top k elements in heap
        Return elements from heap
    Complexity: O(n log k)
 */
public class TopKFrequentElements {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		TopKFrequentElements obj = new TopKFrequentElements();
		
		List<Integer> list = obj.topKFrequent(nums, k);
		System.out.println(Arrays.toString(list.toArray()));
	}

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        
        //Find frequency of numbers
        for(int num : nums){
            if(map.containsKey(num)){
                int freq = map.get(num);
                map.put(num, freq+1);
            }else{
                map.put(num, 1);
            }
        }
        
        //Using MinHeap
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //Min Heap
                return o1.getValue() - o2.getValue();    //Value in map is frequency
            }
        });
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
        	if(i < k){
        		pq.offer(entry);    //add into heap till less than k
        	} else {                //after that check new entries with lowest freq entry i.e root
        		if(entry.getValue() > pq.peek().getValue()){    //if more freq than root, replace root with new entry
        			pq.poll();
        			pq.offer(entry);
        		}
        	}
        	i++;
        }
        
        //Heap just has top k entries. So move keys from min heap to list
        while(!pq.isEmpty()){
        	list.add(pq.poll().getKey());
        }
        
        return list;
    }
	
	/*class MinHeapComparator implements Comparator<Map.Entry<Integer, Integer>>{
		public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
			//Ascending order: so min heap
			return o1.getValue() - o2.getValue();
		}		
	}*/

}
