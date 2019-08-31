package lc5xx;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class lc572 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void tree2strbuilder(TreeNode s, StringBuilder sb) {
        if (s == null) return;
        if (s.left != null) {
            sb.append('(');
            tree2strbuilder(s.left, sb);
            sb.append(')');
        }
        sb.append(s.val);
        if (s.right != null) {
            sb.append('(');
            tree2strbuilder(s.right, sb);
            sb.append(')');
        }
    }

    private String tree2str(TreeNode tn) {
        StringBuilder sb = new StringBuilder();
        tree2strbuilder(tn, sb);
        return sb.toString();
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        String ss = tree2str(s);
        String ts = tree2str(t);
        int index = ss.indexOf(ts);
        return index >= 0;
    }

}
