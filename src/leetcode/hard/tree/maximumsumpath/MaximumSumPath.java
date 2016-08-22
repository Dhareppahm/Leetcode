package leetcode.hard.tree.maximumsumpath;

/**
 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 124. Binary Tree Maximum Path Sum

 Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the
 parent-child connections. The path does not need to go through the root.

 For example:
 Given the below binary tree,

   1
  / \
 2   3
 Return 6.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. At each node, the potential maximum path could be one of these cases:
         i. max(left subtree) + node
         ii. max(right subtree) + node
         iii. max(left subtree) + max(right subtree) + node
         iv. the node itself
    2. Recursively check for left sum, right sum. Return maximum of both + curr.data
    3. Also keep track of maxSum and update if greater value found.
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

    public void displayInorder(Node node){
        if(node != null){
            displayInorder(node.left);
            System.out.print(node.data + " ");
            displayInorder(node.right);
        }
    }

    /**
     * Find Max Path Sum
     */
    private int maxSum = Integer.MIN_VALUE;                         //init to minimum value as if only 1 -ve value is present, return it
    public int maxPathSum(Node root) {
        //Helper Function to call recursively and update maxSum
        findMax(root);
        return maxSum;
    }

    //Helper function
    private int findMax(Node curr){
        if(curr == null) {
            return 0;
        }

        int leftSum = findMax(curr.left);
        int rightSum = findMax(curr.right);

        maxSum = Math.max(leftSum + rightSum + curr.data, maxSum);      //update maxSum

        int sum = curr.data + Math.max(leftSum, rightSum);              //calculate current sum and return for recursive call

        return sum > 0 ? sum : 0;                                       //if sum < 0, return 0 else return sum
    }
}

public class MaximumSumPath {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.root = new Node(-1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(-4);
        bt.root.left.left = new Node(2);
        bt.root.left.right = new Node(-3);

        System.out.println("Binary Tree: ");
        bt.displayInorder(bt.root);

        int maxSum = bt.maxPathSum(bt.root);
        System.out.println("\nMax Sum = " + maxSum);
    }
}
