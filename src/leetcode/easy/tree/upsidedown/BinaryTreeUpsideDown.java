package leetcode.easy.tree.upsidedown;

/**
 32. Binary Tree Upside Down
 https://oj.leetcode.com/problems/binary-tree-upside-down/

 Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
 or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes.
 Return the new root.
 -------------------------------------------------------------------------------------------------------------------------
 Approach: (Use Bottom-up approach as its simple and straight forward to implement)
    Two main statementsto make upside-down, do it recursively:
        curr.left = (parent == null) ? parent : parent.right;
        curr.right = parent
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

    /**
     * Create Upside Down Binary Tree
     */
    public Node upsideDownBinaryTree(Node root) {
        //pass current node and its parent node, recursively to helper
        return upsideDownHelper(root, null);
    }

    private Node upsideDownHelper(Node curr, Node parent) {
        if(curr == null) {
            return parent;
        }

        Node root = upsideDownHelper(curr.left, curr);  //find root and return it

        //Core Logic: to turn upside down
        curr.left = parent == null ? parent : parent.right;
        curr.right = parent;

        return root;
    }
}


public class BinaryTreeUpsideDown {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.right = new Node(5);

        System.out.println("Original Binary Tree: ");
        bt.displayInorder(bt.root);

        System.out.println("\nUpside down Binary Tree: ");
        bt.root = bt.upsideDownBinaryTree(bt.root);

        bt.displayInorder(bt.root);
    }
}
