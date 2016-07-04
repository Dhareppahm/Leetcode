package leetcode.easy.linkedlist.mergelists;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * 21. Merge Two Sorted Lists
 *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes
 * of the first two lists.
 * ------------------------------------------------------------------------------------------------------------------------
 Approach: (Sorted in Increasing order)
    1. Imp to have a dummyHead here as we are inserting at behind, since list is sorted in increasing order
    2. Take a resultCurr, and add smaller Node first, then move resultCurr further, till both lists have Nodes
    3. Add rest of list 1
    4. Add rest of list 2

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
    public void insertFirst(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //Display LinkedList
    public void display(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    //Merge lists
    public Node mergeTwoLists(Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;

        Node dummyHead = new Node(0);           ////VV.V.V.imp to have a dummyHead here as we are inserting at behind
        Node resultCurr = dummyHead;

        //Add from both lists till either list ends
        while(curr1 != null && curr2 != null) {
            if(curr1.data < curr2.data){   //V. imp logic => insert behind logic, so first add smaller one
                resultCurr.next = curr1;
                curr1 = curr1.next;
            }else{
                resultCurr.next = curr2;
                curr2 = curr2.next;
            }
            resultCurr = resultCurr.next;   //move resultCurr forward
        }

        //Add rest from list1
        if(curr1 != null){
            resultCurr.next = curr1;
        }

        //Add rest from list2
        if(curr2 != null){
            resultCurr.next = curr2;
        }

        //Return dummyHead.next which is actual start of mergedList. resultCurr will be at end of merged list so using dummyHead is IMP
        return dummyHead.next;
    }

}

public class MergeLists {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();

        list1.insertFirst(50);
        list1.insertFirst(40);
        list1.insertFirst(30);
        list1.insertFirst(20);
        list1.insertFirst(10);

        System.out.println("List 1:");
        list1.display();

        LinkedList list2 = new LinkedList();

        list2.insertFirst(65);
        list2.insertFirst(55);
        list2.insertFirst(45);
        list2.insertFirst(35);
        list2.insertFirst(25);
        list2.insertFirst(15);
        list2.insertFirst(5);

        System.out.println("\nList 2:");
        list2.display();

        LinkedList mergedList = new LinkedList();
        mergedList.head = mergedList.mergeTwoLists(list1.head, list2.head);
        System.out.println("\nMerged List:");
        mergedList.display();
    }
}
