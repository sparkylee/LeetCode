package lc9xx;

public class lc998 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        TreeNode right = insertIntoMaxTree(root.right, val);
        root.right = right;
        return root;
    }
}
