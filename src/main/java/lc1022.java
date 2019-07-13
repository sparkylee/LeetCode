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

    private Integer sumRootToLeaf(TreeNode root, int sum) {
        if (root == null) return null;
        sum <<= 1;
        sum += root.val;
        Integer sumLeft = sumRootToLeaf(root.left, sum);
        Integer sumRight = sumRootToLeaf(root.right, sum);
        if (sumLeft == null && sumRight == null) return sum;
        return (sumLeft == null ? 0 : sumLeft) + (sumRight == null ? 0 : sumRight);
    }

    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }
}
