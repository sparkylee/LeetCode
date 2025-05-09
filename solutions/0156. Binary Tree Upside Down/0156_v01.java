/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    public TreeNode udBinaryTree(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode rightUD = udBinaryTree(right);
        return null;
    }
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root==null){
            return null;
        }
        if (root.left == null) {
            return root;
        }
        TreeNode rootNew = upsideDownBinaryTree(root.left);
        TreeNode left = root.left;
        TreeNode right = root.right;
        left.left = right;
        left.right = root;
        root.right = null;
        root.left  = null;
        return rootNew;
    }
}