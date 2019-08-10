import org.junit.Test;

public class lc653 {
    @Test
    public void test() {
        TreeNode n5 = TN(5), n3 = TN(3), n2 = TN(2),
                n4 = TN(4), n6 = TN(6), n7 = TN(7);
        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n6.right = n7;
        findTarget(n5, 9);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode TN(int x) {
        return new TreeNode(x);
    }

    private boolean findTarget(TreeNode node, TreeNode root, int k) {
        if (node == null) return false;
        if (findTarget(node.left, root, k)) return true;
        int second = k - root.val;
        if (root.val >= second) return false;
        if (findSecond(root, second)) return true;
        return findTarget(node.right, root, k);
    }

    private boolean findSecond(TreeNode root, int value) {
        if (root == null) return false;
        if (value < root.val) return findSecond(root.left, value);
        if (root.val == value) return true;
        return findSecond(root.right, value);
    }

    public boolean findTarget(TreeNode root, int k) {
        return findTarget(root, root, k);
    }
}
