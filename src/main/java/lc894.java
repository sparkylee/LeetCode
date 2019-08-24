import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc894 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode copy(TreeNode root) {
        if (root == null) return null;
        TreeNode r = new TreeNode(0);
        r.left = copy(root.left);
        r.right = copy(root.right);
        return r;
    }

    public List<TreeNode> allPossibleFBT(int N) {
        List<List<TreeNode>> results = new ArrayList<>();
        List<TreeNode> t1 = new ArrayList<>();
        t1.add(new TreeNode(0));
        for (int i = 1; i <= N / 2; i++) {
            List<TreeNode> ti = new ArrayList<>();
            int n = i * 2 + 1;
            for (int j = 1; j < n; j += 2) {
                int k = n - j;
                List<TreeNode> leftList = results.get(j / 2);
                List<TreeNode> rightList = results.get(k / 2);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode r = new TreeNode(0);
                        r.left = copy(left);
                        r.right = copy(right);
                        ti.add(r);
                    }
                }
            }
            results.add(ti);
        }
        return results.get(N / 2);
    }
}
