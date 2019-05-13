import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc230
{

//    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode TN(Integer x)
    {
        if(x==null) return null;
        return new TreeNode(x);
    }
    private TreeNode [] createTNs(Integer [] nums)
    {
        TreeNode [] treeNodes = new TreeNode[nums.length];
        for(int i=0;i<nums.length; i++)
            treeNodes[i] = TN(nums[i]);
        return treeNodes;
    }
    private TreeNode linkTNs(TreeNode [] treeNodes)
    {
        if(treeNodes==null || treeNodes.length < 1)
            return null;
        int i = 0;
        int len = treeNodes.length;
        int count = 1;
        int j = count;
        while(true)
        {
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

    private void testTreeNodes(Integer[] nums, int k) {
        TreeNode root = createTreeNodeRoot(nums);
        int result = kthSmallest(root, k);
        System.out.println(result);
    }
    @Test
    public void test1()
    {
        Integer[] nums = {3, 1, 4, null, 2};
        testTreeNodes(nums, 1);
    }

    @Test
    public void test2() {
        Integer[] nums = {5, 3, 6, 2, 4, null, null, 1};
        testTreeNodes(nums, 3);
    }

    //////////////////////
    boolean found = false;
    int result = 0;
    int count = 0;

    void countLeft(TreeNode root, int k)
    {
        if(this.found || root==null)
            return;
        countLeft(root.left, k);
        this.count ++;
        if (this.count == k)
        {
            this.found = true;
            this.result = root.val;
            return;
        }
        countLeft(root.right, k);
        return;
    }
    public int kthSmallest(TreeNode root, int k) {
        countLeft(root,k);
        return this.result;
    }

}
