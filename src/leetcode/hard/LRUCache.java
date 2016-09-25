package leetcode.hard;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 146. LRU Cache

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
                    it should invalidate the least recently used item before inserting a new item.
 -------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Use LinkedHashMap as it maintains insertion order.
    2. Create a class and extend LinkedHashMap and override method removeEldestEntry, to return true if no.of elements are more
        than capacity of map (KEY STEP) Otherwise it keeps on inserting items in map
 */

public class LRUCache {

    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LRUMap<>(capacity);        //use linkedhashmap as it maintains insertion order
    }

    public int get(int key) {
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            return -1;
        }
    }

    public void set(int key, int value) {
        map.put(key,value);
    }

    //Driver function
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.set(1,100);
        lru.set(2,200);
        lru.set(3,300);
        lru.set(4,400);

        System.out.println(lru.map);
        System.out.println(lru.get(3));
        System.out.println(lru.get(1));
    }
}

//LRU Map class which extends LinkedHashMap and override removeEldestEntry method
class LRUMap<K,V> extends LinkedHashMap<K,V>{

    int cacheSize;

    //Constructor
    public LRUMap(int capacity){
        //super(capacity, 0.75f, true) ; //access-based eviction set to 'true' (as opposed to 'insertion'-based eviciton)
        cacheSize = capacity ;
    }

    //KEY : Override LinkedHashMap method, which by default returns false, so elements can keep on being added
    //We return true, when size() > cacheSize. So we remove elements after increasing cacheSize
    /**
     * In other words, after put operation, LinkedHashMap will call this function, and if this function returns true,
     * it will remove the eldest entry. Therefore, we need to make sure it returns true if and only if map.size()
     * is greater than the CAPACITY specified.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > cacheSize;
    }
}
