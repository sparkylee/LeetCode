import org.junit.Test;

public class lc551 {
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

    public boolean checkRecord(String s) {
        int ac = 0;
        int lc = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') ac++;
            if (c == 'L') {
                lc++;
            } else {
                lc = 0;
            }
            if (ac > 1 || lc > 2) return false;
        }
        return true;
    }
}
