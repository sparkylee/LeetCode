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
     private boolean equals(TreeNode x, TreeNode y) {
        if (x == y) return true;
        if (x == null || y == null || x.val != y.val) return false;
        return equals(x.left, y.left) && equals(x.right, y.right);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        if (equals(s, t)) return true;
        if (isSubtree(s.left, t))
            return true;
        if (isSubtree(s.right, t))
            return true;
        return false;
    }


}