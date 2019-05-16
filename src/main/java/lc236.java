import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc236 {

    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode TN(Integer x) {
        if (x == null) return null;
        return new TreeNode(x);
    }

    private TreeNode[] createTNs(Integer[] nums) {
        TreeNode[] treeNodes = new TreeNode[nums.length];
        for (int i = 0; i < nums.length; i++)
            treeNodes[i] = TN(nums[i]);
        return treeNodes;
    }

    private TreeNode linkTNs(TreeNode[] treeNodes) {
        if (treeNodes == null || treeNodes.length < 1)
            return null;
        int i = 0;
        int len = treeNodes.length;
        int count = 1;
        int j = count;
        while (true) {
            int jj = 0;
            for (int ii = 0; ii < count; ii++) {
                if (treeNodes[i + ii] == null)
                    continue;
                if (j + jj < treeNodes.length)
                    treeNodes[i + ii].left = treeNodes[j + jj];
                else
                    return treeNodes[0];
                jj++;
                if (j + jj < treeNodes.length)
                    treeNodes[i + ii].right = treeNodes[j + jj];
                else
                    return treeNodes[0];
                jj++;
            }
            i = j;
            count <<= 1;
            j = count + j;
        }
    }

    private TreeNode createTreeNodeRoot(Integer[] nums) {
        TreeNode[] treeNodes = createTNs(nums);
        return linkTNs(treeNodes);
    }

    class Record {
        TreeNode node;
        boolean left;
        boolean right;

        Record(TreeNode node, boolean left, boolean right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }

    List<Record> records = new ArrayList<>();

    private boolean search4Node(TreeNode root, TreeNode tn, boolean left, boolean right) {
        Record r = new Record(root, false, false);
        if (root == tn)
            return true;

        if (root == null)
            return false;
        records.add(r);
        if (left) {
            boolean found = search4Node(root.left, tn, true, true);
            if (found)
                return true;
        }
        if (right) {
            boolean found = search4Node(root.right, tn, true, true);
            if (found) return found;
        }
        return false;
    }

    private TreeNode search4AnotherNode(TreeNode root, TreeNode tn, boolean left, boolean right) {
        for (int i = records.size() - 1; i >= 0; i--) {
            Record r = records.get(i);
            boolean found = search4Node(r.node, tn, !r.left, !r.right);
            if (found)
                return r.node;
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

}
