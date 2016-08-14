package leetcode.easy.tree.lca;

/**
 236. Lowest Common Ancestor of a Binary Tree
 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 _______3______
 /              \
 ___5__          ___1__
 /      \        /      \
 6      _2       0       8
 /  \
 7   4
 For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 ------------------------------------------------------------------------------------------------------------------------
 Approach 1: Recursion
 1) Traverse the tree starting from root. If any of the given nodes (n1 and n2) matches with root, then root is LCA.
 2) If root doesn't match with any of the nodes, we recur for left and right subtrees.
 The node which has n1 present in its one subtree and the n2 present in other subtree is the LCA.
 3) If both nodes lie in left subtree, then left subtree has LCA, otherwise LCA lies in right subtree.

 Approach 2: Iterative
 1) Find path from root to n1 and store it in LinkedList<Node>.
 2) Find path from root to n2 and store it in another LinkedList<Node>.
 3) Traverse both paths till the values in list are same. Return the common node just before the mismatch.
 */
class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Tree{
    Node root;

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root == null){       //base condition
            return null;
        }

        if(root == p || root == q){         //check condition that node can be a descendant of itself
            return root;
        }

        Node leftLCA = lowestCommonAncestor(root.left, p, q);
        Node rightLCA = lowestCommonAncestor(root.right, p, q);

        if(leftLCA != null && rightLCA != null){        //p and q present in left and right subtree, return root
            return root;
        }

        return leftLCA != null ? leftLCA : rightLCA;    //else return which ever not null
    }

}
public class LowestCommonAncestor {

    public static void main(String[] args) {
        Tree tree = new Tree();
        /**
                1
            /       \
           2         3
         /  \       / \
       4    5     6   7
      / \
    8    9

         */
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.left.left.left = new Node(8);
        tree.root.left.left.right = new Node(9);

        System.out.println("Tree is: ");
        tree.displayInorder(tree.root);

        //To handle case, when p or q not present, first check if they are present.
        Node p = tree.root.left.left.right;
        Node q = tree.root.left.right;

        //By Recursion
        Node lca = tree.lowestCommonAncestor(tree.root, p, q);
        System.out.println("\nLCA (Recursion) is : "+ lca.data);

    }
}
