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
   public int convertBSTRe(TreeNode root, int v) {
        if(root==null) return v;
        int vr = convertBSTRe(root.right, v);
        root.val += vr;
        int vl = convertBSTRe(root.left, root.val);
        return vl;
    }
    public TreeNode convertBST(TreeNode root) {
        convertBSTRe(root, 0);
        return root;
    }
}