package leetcode.easy.linkedlist.removenthnodefromend;

/**
 https://leetcode.com/problems/remove-nth-node-from-end-of-list/

 19. Remove Nth Node From End of List
 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.

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

    //Display LinkedList
    public void display(Node n) {
        Node curr = n;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     A one pass solution can be done using pointers. Move one pointer fast --> n+1 places forward,
     to maintain a gap of n between the two pointers and then move both at the same speed.
     Finally, when the fast pointer reaches the end, the slow pointer will be n+1 places behind - just the right spot for
     it to be able to skip the next node.
     */
    public Node removeNthFromEnd(Node head, int n) {

        Node dummyHead = new Node(0);
        dummyHead.next = head;

        Node curr = dummyHead;              //slow ptr
        Node fast = dummyHead;              //fast ptr

        //Move fast in front so that the gap between slow and fast becomes n
        while (n > 0){
            fast = fast.next;
            n--;
        }

        //move fast till end of list
        while(fast.next != null){
            curr = curr.next;
            fast = fast.next;
        }

        System.out.println("\nCurr: " + curr.data);
        System.out.println("Fast: " + fast.data);

        //delete curr.next node
        curr.next = curr.next.next;
        return dummyHead.next;
    }

}

public class RemoveNthNodeFromEndofList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(1);
        //list.head.next = new Node(2);
        //list.head.next.next = new Node(3);
        /*list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);
        list.head.next.next.next.next.next.next = new Node(7);*/

        list.display(list.head);
        Node newHead = list.removeNthFromEnd(list.head, 1);
        System.out.println("New List:");
        list.display(newHead);
    }
}
