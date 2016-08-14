package leetcode.easy.tree.lcabst;

/**
 235. Lowest Common Ancestor of a Binary Search Tree
 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the
 lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

           ___6______
        /              \
    ___2__          ___8__
   /      \        /      \
  0      _4       7       9
 /  \
 3   5

 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
 since a node can be a descendant of itself according to the LCA definition.
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

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    /**
     *  LCA for BST : Making use of BST property
     1. If both n1 and n2 are smaller than root, then LCA lies in left
     2. If both n1 and n2 are greater than root, then LCA lies in right
     3. Else LCA is the node itself
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q) {

        if(root == null){   //base condition
            return null;
        }

        if(p.data < root.data && q.data < root.data){       //If both n1 and n2 are smaller than root, then LCA lies in left
            return lowestCommonAncestor(root.left, p, q);
        }

        if(p.data > root.data && q.data > root.data){       //If both n1 and n2 are greater than root, then LCA lies in right
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}

public class LowestCommonAncestorBST {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        /**
                    4
                  /   \
                 2     5
               /   \
              1     3
         */
        bst.root = new Node(4);
        bst.root.left = new Node(2);
        bst.root.right = new Node(5);
        bst.root.left.left = new Node(1);
        bst.root.left.right = new Node(3);

        bst.displayInorder(bst.root);

        Node p = bst.root.left.left;
        Node q = bst.root.left;

        Node lca = bst.lowestCommonAncestor(bst.root, p, q);
        System.out.println("\nLCA: "+lca.data);


    }
    
}
