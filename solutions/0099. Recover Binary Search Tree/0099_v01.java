/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode maxNode=null, minNode = null;
    private void getMax(TreeNode root)
    {
        if(root==null) return;
        if(maxNode==null || root.val>maxNode.val) maxNode = root;
        getMax(root.left);
        getMax(root.right);
    }
    private void getMin(TreeNode root)
    {
        if(root==null) return;
        if(minNode==null || root.val<minNode.val) minNode = root;
        getMin(root.left);
        getMin(root.right);
    }
    private boolean checkConsistency(TreeNode root, TreeNode nodeMin,TreeNode nodeMax)
    {
        if(root==null) return true;
        if((nodeMax !=null && root.val>nodeMax.val) || (nodeMin!=null && root.val<nodeMin.val)) return false;
        return checkConsistency(root.left,nodeMin,root) && checkConsistency(root.right,root,nodeMax);
    }
    private void swapValues(TreeNode n1,TreeNode n2)
    {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        boolean isLeftConsistent =  checkConsistency(root.left,null,root);
        boolean isRightConsistent =  checkConsistency(root.right,root,null);
        this.maxNode = null;
        this.minNode=null;
        if(!isLeftConsistent)
            getMax(root.left);
        if(!isRightConsistent)
            getMin(root.right);
        if(isLeftConsistent && isRightConsistent) return;
        if(!isLeftConsistent && !isRightConsistent)
        {
            swapValues(this.maxNode,this.minNode);
            return;
        }
        if(!isLeftConsistent)
        {
            if(root.val<this.maxNode.val)
            {
                swapValues(root,this.maxNode);
                return;
            }
            else
            {
                recoverTree(root.left);
                return;
            }
        }
        if(!isRightConsistent)
        {
            if(root.val>this.minNode.val)
            {
                swapValues(root,this.minNode);
                return;
            }
            else
            {
                recoverTree(root.right);
                return;
            }
        }
    }
}