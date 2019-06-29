import org.junit.Test;

public class lc617 {
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

    TreeNode add(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        int val = 0;
        val += t1 == null ? 0 : t1.val;
        val += t2 == null ? 0 : t2.val;
        return new TreeNode(val);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root = add(t1, t2);
        if (root == null) return null;
        root.left = mergeTrees(t1 == null ? null : t1.left,
                t2 == null ? null : t2.left);
        root.right = mergeTrees(t1 == null ? null : t1.right,
                t2 == null ? null : t2.right);
        return root;
    }
}
