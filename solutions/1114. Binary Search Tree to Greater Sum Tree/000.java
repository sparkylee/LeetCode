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
  private int bstToGst(TreeNode root, int sum) {
        if (root == null) return sum;
        sum = bstToGst(root.right, sum);
        root.val += sum;
        return bstToGst(root.left, root.val);
    }

    public TreeNode bstToGst(TreeNode root) {
        bstToGst(root, 0);
        return root;
    }
}