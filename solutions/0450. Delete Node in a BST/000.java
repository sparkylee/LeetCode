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
    TreeNode getLENodeMax(TreeNode root) {
        if(root==null)
        {
            return null;
        }
        while(root.right!=null) {
            root=root.right;
        }
        return root;        
    }
    TreeNode getGENodeMin(TreeNode root) {
        if(root==null)
        {
            return null;
        }
        while(root.left!=null) {
            root=root.left;
        }
        return root;        
    }
    TreeNode removeRootNode(TreeNode root) {
        if(root==null) {
            return null;
        }
        if(root.left!=null) {
            // root.val = root.left.val;
            // root.left = removeRootNode(root.left);
            TreeNode maxNode = getLENodeMax(root.left);
            root.val = maxNode.val;
            root.left = deleteNode(root.left, maxNode.val);
            return root;
        }
        if(root.right!=null) {
            // root.val = root.right.val;
            // root.right= removeRootNode(root.right);
            TreeNode minNode = getGENodeMin(root.right);
            root.val = minNode.val;
            root.right= deleteNode(root.right, minNode.val);           
            return root;
        }
        return null;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) {
            return null;
        }
        if(root.val==key) {
            return removeRootNode(root);
        }
        if(root.val>key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
         if(root.val<key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        return root;
    }
}