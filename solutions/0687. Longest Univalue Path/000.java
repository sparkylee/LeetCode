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
     int count = 0;

    private int longestPath(TreeNode root, Integer val) {
        if (root == null) return 0;
        int lv = longestPath(root.left, root.val);
        int rv = longestPath(root.right, root.val);
        int v = lv + rv;
        this.count = Math.max(v, this.count);
         if (val!=null && root.val==val ) //
            return Math.max(lv,rv)+1;    
        return 0;
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        longestPath(root, null);
        return this.count;
    }
}