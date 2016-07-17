package leetcode.easy.tree.invertbinarytree;

/**
 https://leetcode.com/problems/invert-binary-tree/
 https://leetcode.com/articles/invert-binary-tree/
 Invert a binary tree.

      4
    /   \
   2     7
  / \   / \
 1   3 6   9

 to

      4
    /   \
   7     2
  / \   / \
 9   6 3   1

 Trivia:
 This problem was inspired by this original tweet by Max Howell:
 Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
 ---------------------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Make right subtree, left so store it in temp
    2. Make left subtree in right
    3. Copy right subtree to left
    Note: Imp base condition to terminate recursion
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

    public void displayInorder(Node root){
        if(root != null){
            displayInorder(root.left);
            System.out.print(root.data + " ");
            displayInorder(root.right);
        }
    }

    public Node invertTree(Node root) {
        if(root == null){                   //Imp, base condition to terminate recursion
            return root;
        }

        //Need to store in temp, else left is over-written by right tree
        Node temp = invertTree(root.right);  //Make root's right -> root left
        root.right = invertTree(root.left);      //Make root's left -> root right

        root.left = temp;
        return root;                             //return root
    }

}

public class InvertBinaryTree {

    public static void main(String[] args) {
        BinaryTree ibt = new BinaryTree();

        ibt.root = new Node(4);
        ibt.root.left = new Node(2);
        ibt.root.right = new Node(7);
        ibt.root.left.left = new Node(1);
        ibt.root.left.right = new Node(3);
        ibt.root.right.left = new Node(6);
        ibt.root.right.right = new Node(9);

        System.out.println("Original Tree: ");
        ibt.displayInorder(ibt.root);

        ibt.root = ibt.invertTree(ibt.root);
        System.out.println("\nInverted Tree: ");
        ibt.displayInorder(ibt.root);
    }
}
