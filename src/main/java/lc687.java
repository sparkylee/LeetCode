public class lc687 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int count = 0;

    private int longestPath(TreeNode root, int val) {
        if (root == null) return -1;
        if (root.val != val) // new root
        {
            longestUnivaluePath(root);
            return -1;
        }
        int lv = longestPath(root.left, val);
        int rv = longestPath(root.right, val);
        int v = Math.max(lv, rv);
        if (v == -1) return 0;
        return v + 1;
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int lv = longestPath(root, root.val);
        int rv = longestPath(root, root.val);
        int v = lv + rv;
        this.count = Math.max(v, this.count);
        return this.count;
    }

}
