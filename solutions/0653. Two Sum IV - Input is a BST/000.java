/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  private boolean findTarget(TreeNode node, TreeNode root, int k) {
        if (node == null) return false;
        if (findTarget(node.left, root, k)) return true;
        int second = k - node.val;
        if (node.val >= second) return false;
        if (findSecond(root, second)) return true;
        return findTarget(node.right, root, k);
    }

    private boolean findSecond(TreeNode root, int value) {
        if (root == null) return false;
        if (value < root.val) return findSecond(root.left, value);
        if (root.val == value) return true;
        return findSecond(root.right, value);
    }

    public boolean findTarget(TreeNode root, int k) {
        return findTarget(root, root, k);
    }
}