package leetcode.medium.tree.validatebst;
/*
https://leetcode.com/problems/validate-binary-search-tree/

98. Validate Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
----------------------------------------------------------------------------------------------------------
Approach:
	1. Do traversal and keep track of MIN and MAX values allowed. Return true if constraints valid for all nodes
	2. Do InOrder traversal, check if sequence is Increasing Monotonic sequence
*/

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class BinarySearchTree{
    Node root;

    public BinarySearchTree(){
        root = null;
    }

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    //Approach 1
    public boolean isValidBST_MinMax(Node node){
        return isValidBSTUtil(node, null, null);            //min/max - pass null to handle MIN/MAX node data values
    }

    private boolean isValidBSTUtil(Node node, Integer min, Integer max) {
        if(node == null) {       //empty tree is BST
            return true;
        }

        //otherwise check the subtrees recursively tightening the min/max constraints */
        return (min == null || node.data > min) &&                  //check value is greater than min and less than max
                (max == null || node.data < max) &&
                isValidBSTUtil(node.left, min, node.data) &&      //left subtree
                isValidBSTUtil(node.right, node.data, max);       //right subtree
    }

    //Approach 2
    Node prev;                                              // To keep tract of previous node in Inorder Traversal

    public boolean isValidBST_ByInorder(Node node){
        prev = null;
        return isMonotonicIncreasing(node);
    }

    private boolean isMonotonicIncreasing(Node node) {
        if (node == null) return true;
        if (isMonotonicIncreasing(node.left)) {                //if left ok
            if (prev != null && node.data <= prev.data) {      //check root, it should be greater than prev
                return false;
            }
            prev = node;                                       //update prev
            return isMonotonicIncreasing(node.right);          //check right
        }
        return false;
    }

}

public class ValidateBST{

    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();

        bst.root = new Node(4);
        bst.root.left = new Node(2);
        bst.root.right = new Node(5);
        bst.root.left.left = new Node(10);
        bst.root.left.right = new Node(3);

        bst.displayInorder(bst.root);

        //Approach 1
        boolean flag = bst.isValidBST_MinMax(bst.root);
        System.out.println(flag);

        flag = bst.isValidBST_ByInorder(bst.root);
        System.out.println(flag);
    }
}