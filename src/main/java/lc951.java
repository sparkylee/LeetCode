import org.junit.Test;

public class lc951 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int[] matched;

    private boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        int val = root1.val;
        if (matched[val] == 1) return true;
        if (matched[val] == -1) return false;
        boolean l1r2Matched = flipEquiv1(root1.left, root2.right);
        boolean r1l2Matched = flipEquiv1(root1.right, root2.left);
        if (l1r2Matched && r1l2Matched) {
            matched[val] = 1;
            return true;
        }
        boolean l1l2Matched = flipEquiv1(root1.left, root2.left);
        boolean r1r2Matched = flipEquiv1(root1.right, root2.right);
        if (l1l2Matched && r1r2Matched) {
            matched[val] = 1;
            return true;
        }
        matched[val] = -1;
        return false;
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        matched = new int[100];
        for (int i = 0; i < 100; i++)
            matched[i] = 0;
        return flipEquiv1(root1, root2);
    }
}
