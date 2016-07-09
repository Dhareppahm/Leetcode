package leetcode.medium.linkedlist.addnumbers;



/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * 2. Add Two Numbers
 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of
 their nodes contain a single digit. Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. add numbers till both list has numbers
    2. If there are more numbers in list 1, add numbers or numbers with carry if carry present
    3. Else If there are more numbers in list 2, add numbers or numbers with carry if carry present
    4. If there is just carry remaining after last addition, add it as new node
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

    //Add two numbers
    public Node addTwoNumbers(Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;

        Node dummyHead = new Node(0);
        Node curr = dummyHead;

        int carry = 0;
        int sum = 0;
        while(curr1 != null && curr2 != null){              //add numbers till both list has numbers
            sum = curr1.data + curr2.data + carry;
            carry = sum / 10;
            sum = sum % 10;

            curr.next = new Node(sum);

            curr = curr.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

                                            //If there are more numbers in list 1
        if(curr1 != null){
            if(carry > 0){
                curr.next = addTwoNumbers(curr1, new Node(carry));
            }else{
                curr.next = curr1;
            }
        }else if(curr2 != null){            //Else if list 2 has more numbers
            if(carry > 0){
                curr.next = addTwoNumbers(curr2, new Node(carry));
            }else{
                curr.next = curr2;
            }
        }else if(carry > 0){            //If there is just carry remaining after last addition, add it as new node
            Node temp = new Node(carry);
            curr.next = temp;
        }

        return dummyHead.next;
    }
}



public class AddTwoNumbers {
    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
        list1.insertFirst(3);
        list1.insertFirst(4);
        list1.insertFirst(2);

        System.out.println("List 1: ");
        list1.display();

        LinkedList list2 = new LinkedList();
        list2.insertFirst(4);
        list2.insertFirst(6);
        list2.insertFirst(5);
//        list2.insertFirst(9);

        System.out.println("\nList 2: ");
        list2.display();

        LinkedList addedList = new LinkedList();
        addedList.head = addedList.addTwoNumbers(list1.head, list2.head);
        System.out.println("\nAdded List: ");
        addedList.display();
    }
}
