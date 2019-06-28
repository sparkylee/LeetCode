import org.junit.Test;

public class lc538 {
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
        TreeNode tn5 = TN(5), tn2 = TN(2), tn13 = TN(13);
        tn5.left = tn2;
        tn5.right = tn13;
        convertBST(tn5);
        System.out.println(tn5.val);
        System.out.println(tn2.val);
        System.out.println(tn13.val);
    }

    @Test
    public void t1() {
        TreeNode tn5 = TN(5);
        convertBST(tn5);
        System.out.println(tn5.val);
    }

    public int convertBSTRe(TreeNode root, int v) {
        if (root == null) return v;
        int vr = convertBSTRe(root.right, v);
        root.val += vr;
        int vl = convertBSTRe(root.left, root.val);
        return vl;
    }

    public TreeNode convertBST(TreeNode root) {
        convertBSTRe(root, 0);
        return root;
    }

}
