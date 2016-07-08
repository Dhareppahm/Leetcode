package leetcode.easy.tree.balanced;

/**
 https://leetcode.com/problems/balanced-binary-tree/
 110. Balanced Binary Tree

 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 of every node never differ by more than 1.
 ----------------------------------------------------------------------------------------------------------
 Approach:
 1. Brute Force: O(n^2) runtime, O(n) stack space
 As at each node we check height of left and right subtree and then check conditions

 Conditions to check if tree is Height Balanced
 IsHeightBalanced(tree):
     return (tree is empty) OR
        (IsHeightBalanced(tree.left) AND
        IsHeightBalanced(tree.right) AND
        abs(Height(tree.left) - Height(tree.right)) <= 1)

 2. Optimal Approach:
     1. We use a sentinel value –1 to represent that the tree is unbalanced so we could avoid unnecessary calculations.
     2. In each step, we look at the left subtree’s depth (L), and ask: “Is the left subtree unbalanced?”
     If it is indeed unbalanced, we return –1 right away. Otherwise, L represents the left subtree’s depth.
     3. We then repeat the same process for the right subtree’s depth (R).
     4. We calculate the absolute difference between L and R. If the subtrees’ depth difference is less than one,
     we could return the height of the current node, otherwise return –1 meaning the current tree is unbalanced.

 */

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class BinaryTree{
    Node root;

    public BinaryTree(){
        root = null;
    }

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    /**
     * Brute Force: O(n^2) runtime, O(n) stack space
     * As at each node we check height of left and right subtree and then check conditions
     *
     Conditions to check if tree is Height Balanced
         IsHeightBalanced(tree):
            return (tree is empty) OR
                (IsHeightBalanced(tree.left) AND
                IsHeightBalanced(tree.right) AND
                abs(Height(tree.left) - Height(tree.right)) <= 1)
     */
    public boolean isHeightBalanced(Node node){
        if(node == null)
            return true;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        System.out.println("left ht ="+leftHeight + " rightHt ="+rightHeight);

        return Math.abs(leftHeight - rightHeight) <= 1 &&
                isHeightBalanced(node.left) &&
                isHeightBalanced(node.right);
    }

    //same max depth function: Time: O(n), Space : O(log n) stack space
    private int getHeight(Node node){
        if(node == null)
            return 0;

        return Math.max(getHeight(node.left), getHeight(node.right))+1;
    }

    /** Optimal Approach:
     1. We use a sentinel value –1 to represent that the tree is unbalanced so we could avoid unnecessary calculations.
     2. In each step, we look at the left subtree’s depth (L), and ask: “Is the left subtree unbalanced?”
        If it is indeed unbalanced, we return –1 right away. Otherwise, L represents the left subtree’s depth.
     3. We then repeat the same process for the right subtree’s depth (R).
     4. We calculate the absolute difference between L and R. If the subtrees’ depth difference is less than one,
        we could return the height of the current node, otherwise return –1 meaning the current tree is unbalanced.

     Time: O(n) and Space: O(n)
     */
    public boolean isBalanced(Node root){
        if(getHeight_1(root) != -1){
            return true;
        }else {
            return false;
        }
    }

    private int getHeight_1(Node root) {
        if(root == null)
            return 0;

        int left = getHeight_1(root.left);              //if left unbalanced, return -1 right away
        if (left == -1) return -1;

        int right = getHeight_1(root.right);            //if right unbalanced, return -1 right away
        if (right == -1) return -1;

        if(Math.abs(left - right) <= 1){            //check balanced condition
            return Math.max(left, right)+1;         //return max height
        }else{
            return -1;                              //if unbalanced return -1
        }
    }
}

public class BalancedBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Tree :");
        tree.displayInorder(tree.root);
        System.out.println();

        boolean flag = tree.isHeightBalanced(tree.root);
        System.out.println(flag);

        flag = tree.isBalanced(tree.root);
        System.out.println(flag);
    }
}