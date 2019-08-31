package lc7xx;

import org.junit.Test;

public class lc783 {
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

    int m = -1;

    private Integer minBST(TreeNode root, Integer mval) {
        if (root == null) return mval;
        mval = minBST(root.left, mval);
        if (mval != null) {
            int mNew = root.val - mval;
            if (m == -1)
                m = mNew;
            else
                m = Math.min(mNew, m);
        }
        return minBST(root.right, root.val);
    }

    public int minDiffInBST(TreeNode root) {
        minBST(root, null);
        return this.m;
    }
}
