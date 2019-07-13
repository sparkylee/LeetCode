public class lc1022 {
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

    private int sumRootToLeaf(TreeNode root, int sum) {
        if (root == null) return sum;
        sum <<= 1;
        sum += root.val;
        int sumLeft = sumRootToLeaf(root.left, sum);
        int sumRight = sumRootToLeaf(root.right, sum);
        return sumLeft + sumRight;
    }

    public int sumRootToLeaf(TreeNode root) {
        return 0;
    }
}
