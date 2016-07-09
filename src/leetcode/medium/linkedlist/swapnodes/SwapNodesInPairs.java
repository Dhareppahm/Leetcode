package leetcode.medium.linkedlist.swapnodes;

/**
 https://leetcode.com/problems/swap-nodes-in-pairs/
 24. Swap Nodes in Pairs
 Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 ---------------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Use temp node to hold prev node of p, during traversal and also 3 pointers: p, q, r : curr, curr.next and curr.next.next respectively
    2. Make q.next point to p and p.next points to r i.e next next node
    3. Make prev next point to q and prev to p
    4. Move p to r as first pairs (2 nodes) swapped

 */

//Represent the Node of linkedlist
class Node {
    int data;
    Node next;
    public Node(int data){
        this.data = data;
        next = null;
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

    public Node swapPairs(Node head){
        //Create a dummy head for simplicity to return
        Node dummyHead = new Node(0);
        dummyHead.next = head;           //dummy next points to main head, used to return

        Node prev = dummyHead;           //A temp node to hold prev node of p, during traversal

        Node p = head;                  //3 pointers: p, q, r : curr, curr.next and curr.next.next respectively
        while(p != null && p.next != null){     //also check p.next != null as its going to be r
            Node q = p.next;
            Node r = q.next;                    //initialize q and r

            q.next = p;                         //Make q.next point to p
            p.next = r;                         //and p.next points to r i.e next next node

            prev.next = q;                      //Make prev next point to q and prev to p
            prev = p;

            p = r;                              //Move p to r as first pairs (2 nodes) swapped
        }

        return dummyHead.next;
    }

}

public class SwapNodesInPairs {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();
//        list.insertFirst(5);
        list.insertFirst(4);
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(1);

        System.out.println("Original List:");
        list.display();

        System.out.println("\nSwapped List:");
        list.head = list.swapPairs(list.head);
        list.display();

    }
}
