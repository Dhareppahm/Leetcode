package leetcode.easy.tree.createfromarray;

/**
 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 108. Convert Sorted Array to Binary Search Tree

 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:      https://www.youtube.com/watch?v=VCTP81Ij-EM
    1. Initialize start = 0, end = length of array - 1
    2. mid = (start + end)/2;
    3. Create a Node (say A) with mid as root
    4. Recursively do the following steps:
        Calculate mid of left subarray, and make it root of left subtree of A
        Calculate mid of right subarray, and make it root of right subtree of A

    Time Complexity: O(n), Space complexity: O(log n) stack space
 */

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

class BinarySearchTree{
    Node root;

    public BinarySearchTree(){
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
        Create BST from Sorted Array
     */
    public Node sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
            return null;

        return createBST(nums, 0, nums.length-1);
    }

    private Node createBST(int[] nums, int start, int end) {
        if(start > end) {          //termination condition
            return null;
        }
        int mid = (start + end)/2;
        Node root = new Node(nums[mid]);

        root.left = createBST(nums, start, mid - 1);                //left subtree link to node
        root.right = createBST(nums, mid + 1, end);                 //right subtree link to node

        return root;
    }

}

public class CreateBSTFromSortedArray {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        int[] nums = {1,2,3,4,5};
        bst.root = bst.sortedArrayToBST(nums);
        bst.displayInorder(bst.root);
    }
}
