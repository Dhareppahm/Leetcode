package leetcode.easy.tree.treedepth;

/**

 https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/
Approach: Max Depth
 1. Use recursion, because each of the left child and right child of a node is a sub-tree itself.
 2. We first compute the max height of left sub-tree, and then compute the max height of right sub-tree.
 3. The maximum depth of the current node is the greater of the two maximums plus one.
 4. For the base case, we look at a tree that is empty, which we return 0.

 https://leetcode.com/problems/minimum-depth-of-binary-tree/
 Approach: Min depth
 1. Consider cases:
     i. The node itself is a leaf node. The minimum depth is one.
    ii. Node that has one empty sub-tree while the other one is non-empty. Return the minimum depth of that non-empty sub-tree.
 2. Height if empty tree is 0
 3. Only right subtree is present, return minimum of right subtree
 4. Only left subtree is present, return minimum of left subtree
 5. Both left and right subtrees present, Return minimum of (left or right subtree) + 1
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
     The maximum height of a binary tree is defined as the number of nodes along the path from the root node to the deepest leaf node.
     Time: O(n), Space : O(log n) stack space
     */
    public int getMaxDepth(Node node) {
        if(node == null)                    //Empty tree depth is 0
            return 0;

        return Math.max(getMaxDepth(node.left), getMaxDepth(node.right)) + 1;   //max of (left or right) + 1
    }


    /**
     Consider cases:
        i. The node itself is a leaf node. The minimum depth is one.
        ii. Node that has one empty sub-tree while the other one is non-empty. Return the minimum depth of that non-empty sub-tree.
     */
    public int getMinDepth(Node node) {
        if (node == null)                //Height if empty tree is 0
            return 0;

        if (node.left == null) {          //Only right subtree is present
            return getMinDepth(node.right) + 1;
        }
        if (node.right == null) {         //Only left subtree is present
            return getMinDepth(node.left) + 1;
        }
        //Return minimum of (left or right subtree) + 1
        return Math.min(getMinDepth(node.left), getMinDepth(node.right)) + 1;
    }
}

public class MinMaxDepthOfTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Tree :");
        tree.displayInorder(tree.root);

        int maxDepth = tree.getMaxDepth(tree.root);
        System.out.println("\nMax depth = "+maxDepth);

        int minDepth = tree.getMinDepth(tree.root);
        System.out.println("Min depth = "+minDepth);
    }
}
