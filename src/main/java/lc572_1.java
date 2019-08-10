public class lc572_1 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val == t.val && isSubtree(s.left, t.left) && isSubtree(s.right, t.right))
            return true;
        if (isSubtree(s.left, t))
            return true;
        if (isSubtree(s.right, t))
            return true;
        return false;
    }

}
