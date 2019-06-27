import org.junit.Test;

public class lc530 {
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
        TreeNode tn1 = TN(1), tn2 = TN(2), tn3 = TN(3);
        tn1.right = tn3;
        tn3.left = tn2;
        System.out.println(this.getMinimumDifference(tn1));
    }

    @Test
    public void test2() {
        TreeNode tn1 = TN(1), tn2 = TN(2), tn3 = TN(3);
        tn1.right = tn3;
        tn3.left = tn2;
        System.out.println(this.getMinimumDifference(tn1));
    }

    int diff = -1;

    private Integer getMinimumDifference(TreeNode root, Integer lastValue) {
        if (root == null) {
            return lastValue;
        }
        Integer lastValueCandidate = getMinimumDifference(root.left, lastValue);
        lastValue = lastValueCandidate == null ? lastValue : lastValueCandidate;
        if (lastValue != null) {
            int absValue = Math.abs(lastValue - root.val);
            if (this.diff == -1)
                this.diff = absValue;
            else
                this.diff = Math.min(this.diff, absValue);
        }
        lastValue = root.val;
        return getMinimumDifference(root.right, lastValue);

    }

    public int getMinimumDifference(TreeNode root) {
        this.diff = -1;
        getMinimumDifference(root, null);
        return this.diff;
    }
}
