package leetcode.easy.linkedlist.reverselist;

/**
 206. Reverse Linked List
 https://leetcode.com/problems/reverse-linked-list/

 Reverse a singly linked list.

 Hint:
 A linked list can be reversed either iteratively or recursively. Could you implement both?
 -----------------------------------------------------------------------------------------------------------------------
 Iterative Approach & Recursive Approach:
    1. Reverse Logic, traverse on paper to check

 */
class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class LinkedList{
    Node head;

    public LinkedList(){
        head = null;
    }

    public void display(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * Iterative: Reverse Linkedlist
     * Time : O(n) Space: O(1)
     */
    public Node reverseList_Iterative(Node head) {
        Node prev = null;
        Node next = null;
        Node curr = head;

        while(curr != null){            //Imp: Reverse Logic, traverse on paper to check
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        //OR head = prev; return head;      //prev was set to current before current moved forward. So can return prev
        return prev;
    }

    /**
     * Recursive
     * Time: O(n) and O(n) stack space
     */
    public Node reverseList_Recursive(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node second = head.next;      //get head's next node as second node
        head.next = null;           //set first nodes next to null

        Node newHead = reverseList_Recursive(second);
        second.next = head;

        return newHead;
    }
}

public class ReverseList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);
        list.head.next.next.next.next.next.next = new Node(7);

        System.out.println("Original List : ");
        list.display(list.head);

        System.out.println("\nReverse List : ");
//        list.head = list.reverseList_Iterative(list.head);
        list.head = list.reverseList_Recursive(list.head);
        list.display(list.head);

    }
}
