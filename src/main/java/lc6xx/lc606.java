package lc6xx;

import org.junit.Test;

public class lc606 {
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
    public void test1() {
        TreeNode tn1 = TN(1), tn2 = TN(2), tn4 = TN(4), tn3 = TN(3);
        tn1.left = tn2;
        tn2.left = tn4;
        tn1.right = tn3;
        t(tn1);
    }

    @Test
    public void test2() {
        TreeNode tn1 = TN(1), tn2 = TN(2), tn4 = TN(4), tn3 = TN(3);
        tn1.left = tn2;
        tn2.right = tn4;
        tn1.right = tn3;
        t(tn1);
    }

    @Test
    public void test3() {
        TreeNode tn1 = TN(1);
        t(tn1);
    }

    @Test
    public void test4() {
        t(null);
    }

    public void t(TreeNode t) {
        System.out.println(tree2str(t));
    }

    private void appendNode(StringBuilder parent, TreeNode node) {
        parent.append('(');
        StringBuilder sbx = tree2strRe(node);
        if (sbx != null) parent.append(sbx);
        parent.append(')');
    }

    public StringBuilder tree2strRe(TreeNode t) {
        if (t == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        if (t.left != null || t.right != null) appendNode(sb, t.left);
        if (t.right != null) appendNode(sb, t.right);
        return sb;
    }

    public String tree2str(TreeNode t) {
        if (t == null) return "";
        return tree2strRe(t).toString();
    }
}
