package leetcode.hard.linkedlist.mergeKlists;

/**
 https://leetcode.com/problems/merge-k-sorted-lists/
 23. Merge k Sorted Lists

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 ----------------------------------------------------------------------------------------------------------------------

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

    /**
     Approach: Using Divide and Conquer using two way merge: Time O(nk logk), Space O(1)
     lists[] contains head nodes of all k sorted lists

     First compare and merge First and Last lists => result 1   (use mergeTwoList code to combine 2 lists)
     First compare and merge 2nd and 3rd lists => result 2
     Then compare and merge, Result 1 and Result 2 => Final Merged List
     */
    public Node mergeKLists(Node[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }
        int end = lists.length - 1;
        while(end > 0){
            int begin = 0;
            while(begin < end){
                Node beginHead = lists[begin];
                Node endHead = lists[end];
                lists[begin] = mergeTwoLists(beginHead, endHead);   //update begins head with merge list head, final head will be at [0]

                begin++;
                end--;
            }
        }
        return lists[0];
    }

    //Merge Two lists
    private Node mergeTwoLists(Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;

        Node dummyHead = new Node(0);           ////VV.V.imp to have a dummyHead here as we are inserting at behind
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

public class MergeKLists {
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

        LinkedList list3 = new LinkedList();
        list3.insertFirst(33);
        list3.insertFirst(22);
        list3.insertFirst(11);
        list3.insertFirst(3);
        list3.insertFirst(2);
        list3.insertFirst(1);
        System.out.println("\nList 3:");
        list3.display();

        LinkedList list4 = new LinkedList();
        list4.insertFirst(38);
        list4.insertFirst(19);
        list4.insertFirst(4);
        System.out.println("\nList 4:");
        list4.display();

        LinkedList mergedList = new LinkedList();
        Node[] headsArray = new Node[4];
        headsArray[0] = list1.head;
        headsArray[1] = list2.head;
        headsArray[2] = list3.head;
        headsArray[3] = list4.head;

        mergedList.head = mergedList.mergeKLists(headsArray);
        System.out.println("\nMerged List:");
        mergedList.display();
    }
}
