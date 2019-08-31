package lc2xx;

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

    @Test
    public void test1() {
        Integer[] nums = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        test(nums, 1, 2);
    }

    @Test
    public void test2() {
        Integer[] nums = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        test(nums, 1, 10);
    }

    @Test
    public void test3() {
        Integer[] nums = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        test(nums, 0, 0);
    }

    private void test(Integer[] nums, int pi, int qi) {
        TreeNode[] tnArray = createTNs(nums);
        TreeNode root = linkTNs(tnArray);
        TreeNode p = tnArray[pi];
        TreeNode q = tnArray[qi];
        TreeNode x = lowestCommonAncestor(root, p, q);
        System.out.println(p.val + " " + q.val + " " + x.val);
    }

    ///////////////////////////////////////////
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


    private boolean search4Node(TreeNode root, TreeNode tn) {
        if (root == tn) return true;
        if (root == null) return false;
        return (search4Node(root.left, tn) || search4Node(root.right, tn));
    }

    private boolean searchFirstNode(TreeNode root, TreeNode tn, List<Record> records) {
        if (root == null || tn == null)
            return false;
        Record r = new Record(root, false, false);
        if (root == tn) {
            r.left = false;
            r.right = false;
            records.add(r);
            return true;
        }
        r.left = true;
        r.right = false;
        records.add(r);
        if (searchFirstNode(root.left, tn, records))
            return true;
        r.left = false;
        r.right = true;
        if (searchFirstNode(root.right, tn, records))
            return true;
        records.remove(records.size() - 1);
        return false;
    }

    private TreeNode search4AnotherNode(TreeNode tn, final List<Record> records) {
        for (int i = records.size() - 1; i >= 0; i--) {
            Record r = records.get(i);
            if (r.node == tn)
                return r.node;
            if (!r.left) {
                boolean isFound = search4Node(r.node.left, tn);
                if (isFound) return r.node;
            }
            if (!r.right) {
                boolean isFound = search4Node(r.node.right, tn);
                if (isFound) return r.node;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Record> records = new ArrayList<>();
        searchFirstNode(root, p, records);
        TreeNode x = search4AnotherNode(q, records);
        return x;
    }

}
