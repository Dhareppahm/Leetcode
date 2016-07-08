package leetcode.easy.tree.createfromlist;

/**
 https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 109. Convert Sorted List to Binary Search Tree

 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
------------------------------------------------------------------------------------------------------------------------
 Approach:
    1. We create nodes bottom-up, and assign them to its parents. The bottom-up approach enables us to access the list
        in its order while creating nodes
    2. Maintain list curr node. Take 1st node from list, and create leftchild
    3. Create parent node and assign leftChild to left
    4. Similarly, create rightChild and assign rightChild to right
    5. Return parent node as it will be root at the end
 */

//Represent Nodes of List
class ListNode{
    int data;
    ListNode next;

    public ListNode(int data){
        this.data = data;
    }
}

class LinkedList{
    ListNode head;

    public LinkedList(){
        head = null;
    }

    //Basic Operations : Insert Node at beginning of list & display list
    public void insertFirst(int data){
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
    }

    public void displayList(ListNode root){
        ListNode curr = root;
        while(curr != null){
            System.out.print(curr.data+ " ");
            curr = curr.next;
        }
    }
}

//Represent Nodes of Tree
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    //Basic Operations: Display Tree in Inorder traversal
    public void displayTreeInorder(TreeNode root){
        if(root != null){
            displayTreeInorder(root.left);
            System.out.print(root.data + " ");
            displayTreeInorder(root.right);
        }
    }
}

class BinarySearchTree {
    TreeNode root;
    ListNode curr;

    public BinarySearchTree(){
        root = null;
    }

    public void displayInorder(TreeNode root){
        if(root != null){
            displayInorder(root.left);
            System.out.print(root.data + " ");
            displayInorder(root.right);
        }
    }


    /*
        Create BST from Sorted List
     */
    public TreeNode sortedListToBST(ListNode head) {
        curr = head;
        int length = getListLength(head);

        return createBST(0 , length - 1);
    }

    private TreeNode createBST(int start, int end) {
        if(start > end) {
            return null;
        }
        int mid = (start + end)/2;
        TreeNode leftChild = createBST(start, mid - 1);             //create left child node
        TreeNode parent = new TreeNode(curr.data);                  //create parent node
        parent.left = leftChild;                                    //link leftChild to parent left

        curr = curr.next;                                           //move list curr forward, before moving to right subtree

        TreeNode rightChild = createBST(mid + 1, end);              //create right child node
        parent.right = rightChild;                                  //link rightChild to parent right


        return parent;                                              //return parent as at last it will be root
    }

    //Utility to get list length
    public int getListLength(ListNode head){
        ListNode n = head;
        int len = 0;
        while(n != null){
            n = n.next;
            len++;
        }
        return len;
    }

}

public class CreateBSTFromList {
    public static void main(String[] args) {
        LinkedList sortedList = new LinkedList();
        sortedList.insertFirst(5);
        sortedList.insertFirst(4);
        sortedList.insertFirst(3);
        sortedList.insertFirst(2);
        sortedList.insertFirst(1);

        System.out.println("Sorted List: ");
        sortedList.displayList(sortedList.head);

        BinarySearchTree bst = new BinarySearchTree();
        bst.root = bst.sortedListToBST(sortedList.head);
        System.out.println("\nBST : ");
        bst.displayInorder(bst.root);
    }
}
