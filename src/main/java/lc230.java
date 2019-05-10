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
        int count_next = count << 1;
        while(true)
        {
            int j = 0;
            for(int k = 0; k < count; k++)
            for(j=0;j<count;j++)
            {
                if(j+i>=len)
                    return treeNodes[0];
                if(j%2==0)
                    treeNodes[i+j] = null;
            }


        }
    }
    @Test
    public void test1()
    {
    }

    boolean found = false;
    int result = 0;
    int count = 0;
    int countLeft(TreeNode root, int k)
    {
        if(this.found || root==null)
            return 0;
        int lc = countLeft(root.left, k);
        if(this.found)
            return 0;
        this.count += lc;
        if(this.count==k-1)
        {
            this.found = true;
            this.result = root.val;
        }
        this.count ++;
        if(this.count==k-1)
        {
            this.found = true;
            this.result = root.val;
            return 0;
        }
        lc = countLeft(root.right, k);
        return (this.count + lc);
    }
    public int kthSmallest(TreeNode root, int k) {
        countLeft(root,k);
        return result;
    }

}
