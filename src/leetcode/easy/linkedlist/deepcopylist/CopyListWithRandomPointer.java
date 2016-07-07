package leetcode.easy.linkedlist.deepcopylist;

import java.util.HashMap;
import java.util.Map;

/**
 https://leetcode.com/problems/copy-list-with-random-pointer/
 138. Copy List with Random Pointer

 A linked list is given such that each node contains an additional random pointer that could point to any node in the list or null.
 Return a deep copy of the list. (Do it in Linear time)
 ----------------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Brute Force: O(n^2) as we need to loop for each node to update random pointer
    2. Using HashMap:
        Using a HashMap to store <Current Node, Currents next node> during initial copy of list
        Then start updating random links, get me current nodes random node from map and update random link for newCurr
    3. By Modifying original structure: Very confusing (try later)
 */

//Represent the Node of linkedlist
class Node {
    int data;
    Node next;
    Node random;                //this pointer is random and can point to any Node in list
    public Node(int data){
        this.data = data;
    }
}

class LinkedList {
    Node head;      //points to head linkedlist

    //Initialize LinkedList head to NULL
    public LinkedList() {
        head = null;
    }

    //Insert first
    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        newNode.random = head;                                      //as of now for testing pointing random to next node
        head = newNode;
    }

    //Display LinkedList
    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     *  By Using a HashMap to store <Current Node, Currents next node>
     *  O(n) time and O(n) space
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> randomMap = new HashMap<Node, Node>();

        Node dummyHead = new Node(0);
        Node curr = head;
        Node newCurr = dummyHead;

        //fill the map
        while(curr != null){
            newCurr.next = new Node(curr.data);     //create new copy i.e deep copy
            randomMap.put(curr, newCurr.next);      //Put <Node, and its next> in map

            curr = curr.next;
            newCurr = newCurr.next;
        }

        //start updating random links
        curr = head;
        newCurr = dummyHead;
        while (curr != null){
            newCurr.next.random = randomMap.get(curr.random);             //get me current nodes random node from map
                                                                          //and update random link for newCurr
            curr = curr.next;
            newCurr = newCurr.next;
        }

        return dummyHead.next;
    }

}


public class CopyListWithRandomPointer {
    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
        list1.insertFirst(50);
        list1.insertFirst(40);
        list1.insertFirst(30);
        list1.insertFirst(20);
        list1.insertFirst(10);
        System.out.println("Original List:");
        list1.display();

        LinkedList copyList = new LinkedList();
        copyList.head = copyList.copyRandomList(list1.head);
        System.out.println("\nCopied List:");
        copyList.display();
    }
}
