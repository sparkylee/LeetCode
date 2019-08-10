import java.util.ArrayList;
import java.util.List;

public class lc993 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean findCousins(List<TreeNode> roots, int x, int y) {
        if (roots == null || roots.isEmpty()) return false;
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode xParent = null, yParent = null;
        for (int i = 0; i < roots.size(); i++) {
            TreeNode r = roots.get(i);
            for (TreeNode tn : new TreeNode[]{r.left, r.right}) {
                if (tn != null) {
                    if (tn.val == x) xParent = r;
                    if (tn.val == y) yParent = r;
                    nodes.add(tn);
                }
            }
        }
        if (xParent != null && yParent != null && xParent != yParent) return true;
        if (xParent != null || yParent != null) return false;
        return findCousins(nodes, x, y);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y || x == y) return false;
        List<TreeNode> roots = new ArrayList<>();
        roots.add(root);
        return findCousins(roots, x, y);
    }
}
