package leetcode.easy.tree.sametree;

/*
100. Same Tree
https://leetcode.com/problems/same-tree/
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

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

	/*
	If both are null => Good they must be same
	If either is null => Not Good as they both must be null at same time

	if left subtree is same AND right subtree is same AND node values same => return TRUE
	else => return FALSE
	*/
	public boolean isSameTree(Node p, Node q) {
     	if(p == null && q == null)
     		return true;
     	if(p == null || q == null)
     		return false;
     	boolean isLeftSame = isSameTree(p.left, q.left);
     	boolean isRightSame = isSameTree(p.right, q.right);

    	if(p.data == q.data && isLeftSame && isRightSame)
    		return true;

    	return false;
    }


}

public class SameTree{

	public static void main (String[] args){
		BinaryTree bt1 = new BinaryTree();
		bt1.root = new Node(1);
		bt1.root.left = new Node(2);
		bt1.root.right = new Node(3);
		bt1.root.left.left = new Node(4);
		bt1.root.left.right = new Node(5);
		bt1.root.right.left = new Node(6);

		System.out.println("Tree1 : ");
		bt1.displayInorder(bt1.root);

		BinaryTree bt2 = new BinaryTree();
		bt2.root = new Node(1);
		bt2.root.left = new Node(2);
		bt2.root.right = new Node(3);
		bt2.root.left.left = new Node(4);
		bt2.root.left.right = new Node(5);
		bt2.root.right.left = new Node(6);

		System.out.println("\nTree2 : ");
		bt2.displayInorder(bt2.root);

		boolean result = bt1.isSameTree(bt1.root, bt2.root);
		System.out.println("\n"+result);
	}


}