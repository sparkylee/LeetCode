public class lc814 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = pruneTree(root.left);
        root.left = left;
        TreeNode right = pruneTree(root.right);
        root.right = right;
        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }
}
