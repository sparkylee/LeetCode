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
        int v = 0;
        if (lv >= 0) v += lv + 1;
        if (rv >= 0) v += rv + 1;
        return v;
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int v = longestPath(root, root.val);
        this.count = Math.max(v, this.count);
        return this.count;
    }

}
