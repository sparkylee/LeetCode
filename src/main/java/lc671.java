import org.junit.Test;

public class lc671 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int findNextValue(TreeNode root, int r, int c) {
        if (root == null) return c;
        if (root.val > r) {
            if (root.val < c || c == -1)
                c = root.val;
            return c;
        }
        c = findNextValue(root.left, r, c);
        c = findNextValue(root.right, r, c);
        return c;
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        int r = root.val, c = -1;
        return findNextValue(root, r, -1);
    }
}
