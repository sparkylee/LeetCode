import java.util.ArrayList;
import java.util.List;

public class lc872 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void collectLeaf(TreeNode root, List<TreeNode> collection) {
        if (root == null) return;
        if (root.left == null && root.right == null)
            collection.add(root);
        collectLeaf(root.left, collection);
        collectLeaf(root.right, collection);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<TreeNode> collection1 = new ArrayList<>(),
                collection2 = new ArrayList<>();
        collectLeaf(root1, collection1);
        collectLeaf(root2, collection2);
        int i = 0;
        if (collection1.size() != collection2.size())
            return false;
        while (i < collection1.size() && i < collection2.size()) {
            if (collection1.get(i).val != collection2.get(i).val)
                return false;
            i++;
        }
        return true;
    }
}
