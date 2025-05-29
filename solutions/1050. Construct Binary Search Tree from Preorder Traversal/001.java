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
   private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (start < 0 || end >= preorder.length || start > end) return null;
        int val = preorder[start];
        TreeNode root = new TreeNode(val);
        int rstart = start + 1;
        while (rstart <= end && preorder[rstart] < val) rstart++;
        root.left = bstFromPreorder(preorder, start + 1, rstart - 1);
        root.right = bstFromPreorder(preorder, rstart, end);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }
}