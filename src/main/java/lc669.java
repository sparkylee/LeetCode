import org.junit.Test;

public class lc669 {
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
    public void test() {
        TreeNode tn3 = TN(3), tn0 = TN(0), tn2 = TN(2), tn1 = TN(1), tn4 = TN(4);
        tn3.left = tn0;
        tn0.right = tn2;
        tn3.right = tn4;
        tn2.left = tn1;
        TreeNode root = trimBST(tn3, 1, 3);
        System.out.print(root);
    }

    TreeNode trimRoot(TreeNode root, int L, int R) {
        while (root != null) {
            if (root.val < L) {
                root = root.right;
                continue;
            }
            if (root.val > R) {
                root = root.left;
                continue;
            }
            return root;
        }
        return null;
    }

    void trimLeft(TreeNode root, int L) {
        while (root != null) {
            while (root.left != null && root.left.val < L) {
                root.left = root.left.right;
            }
            root = root.left;
        }
    }

    void trimRight(TreeNode root, int R) {
        while (root != null) {
            while (root.right != null && root.right.val > R) {
                root.right = root.right.left;
            }
            root = root.right;
        }
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        root = trimRoot(root, L, R);
        trimLeft(root, L);
        trimRight(root, R);
        return root;
    }
}
