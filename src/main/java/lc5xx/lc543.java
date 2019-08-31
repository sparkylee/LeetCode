package lc5xx;

import org.junit.Test;

public class lc543 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode TN(int x) {
        return new TreeNode(x);
    }

    @Test
    public void t() {

    }

    private int getHeightFromParent(TreeNode child) {
        if (child == null) return 0;
        return child.val + 1;
    }

    public int diameterOfBinaryTree(TreeNode root, int d) {
        if (root == null) return d;
        d = diameterOfBinaryTree(root.left, d);
        d = diameterOfBinaryTree(root.right, d);
        int hl = getHeightFromParent(root.left);
        int hr = getHeightFromParent(root.right);
        root.val = Math.max(hl, hr);
        return Math.max(hl + hr, d);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return diameterOfBinaryTree(root, 0);
    }
}
