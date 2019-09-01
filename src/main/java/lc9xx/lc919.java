package lc9xx;

import org.junit.Test;

public class lc919 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        TreeNode tn1 = TN(1);
        CBTInserter cbtInserter = new CBTInserter(tn1);
        cbtInserter.insert(2);
        TreeNode r = cbtInserter.get_root();
    }

    private TreeNode TN(int v) {
        return new TreeNode(v);
    }
    class CBTInserter {
        TreeNode root;
        int k;
        int h;

        private int calcHeight(TreeNode root, int height) {
            if (root == null) return height;
            this.k++;
            int h1 = calcHeight(root.left, height + 1);
            int h2 = calcHeight(root.right, height + 1);
            return Math.max(h1, h2);
        }

        public CBTInserter(TreeNode root) {
            this.root = root;
            this.h = calcHeight(root, 0);
        }

        public int insert(int v) {
            TreeNode head = getLastNode(this.root, this.h, this.k);
            TreeNode node = new TreeNode(v);
            if (head.left == null) head.left = node;
            else head.right = node;
            if (this.k == 2 << (this.h) - 1) // new level
                this.h++;
            this.k++;
            return head.val;
        }

        private TreeNode getLastNode(TreeNode root, int h, int k) {
            int c0 = (1 << (h - 1)) - 1;
            int c1 = (1 << h) - 1;
            int half = c0;
            int index = k - c0;
            int delta;
            TreeNode nextRoot;
            if (index == half) return root;
            if (index < half) {
                nextRoot = root.left;
                delta = c0;
            } else {
                nextRoot = root.right;
                delta = c0 + half;
            }
            return getLastNode(nextRoot, h - 1, k - delta);
        }

        public TreeNode get_root() {
            return this.root;
        }
    }
}
