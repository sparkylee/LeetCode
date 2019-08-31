package lc7xx;

public class lc700 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        if (root.val < val)
            return searchBST(root.right, val);
        return searchBST(root.left, val);
    }

}
