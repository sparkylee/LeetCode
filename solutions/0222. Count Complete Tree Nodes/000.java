/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int calculateTreeHeight(TreeNode root) {
        int height  = 0;
        if(root==null) {
            return 0;
        }
        root = root.left;
        while(root!=null) {
            height ++;
            root = root.left;
        }
        return height;
    }
    boolean isPefectTree(TreeNode root, int height) {
        return false;
    }
    int countPerfectTreeNodes(TreeNode root, int height) {
        if (root==null) {
            return 0;
        }
        return (1<<(height + 1)) - 1;
    }
    int countNodes(TreeNode root, int height) {
        if(root == null ) {
            return 0;
        }
        if(height==0) {
            return 1;
        }
        int rightHeight = calculateTreeHeight(root.right);
        int nodes_left, nodes_right;
        if (height==(rightHeight+1)) {
            nodes_left = countPerfectTreeNodes(root.left, height - 1);
            nodes_right = countNodes(root.right, height -1);
        }
        else {
            nodes_left = countNodes(root.left, height - 1);
            nodes_right = countPerfectTreeNodes(root.right, height -2);
        }
         return nodes_left + nodes_right + 1;
    }
    public int countNodes(TreeNode root) {
        int height = calculateTreeHeight(root);
        return countNodes(root, height);
    }
}