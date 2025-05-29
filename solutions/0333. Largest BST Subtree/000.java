/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    class RV {
        boolean isBST;
        int minV;
        int maxV;
        int count;
        RV( boolean isBST, int minV, int maxV, int count) {
            this.isBST = isBST;
            this.minV = minV;
            this.maxV = maxV;
            this.count = count;
        }
    }
    int size = 0;
    RV findlargestBSTSubtree(TreeNode root)  {
        if(root==null) {
            return null;
        }
        RV rv_left = findlargestBSTSubtree(root.left);        
        RV rv_right = findlargestBSTSubtree(root.right);
        int minV = root.val;
        int maxV = root.val;
        RV rv = new RV(false, minV, maxV, 1);
        if(rv_left!=null) {
            if(!rv_left.isBST || root.val <= rv_left.maxV) {
                return rv;
            }              
            rv.minV = rv_left.minV;
            rv.count += rv_left.count;
        }
        if(rv_right!=null) {
            if(!rv_right.isBST || root.val >= rv_right.minV)
            {
                return rv;
            }
            rv.maxV = rv_right.maxV;
            rv.count += rv_right.count;
        }
        rv.isBST = true;
        this.size = Math.max(this.size, rv.count);
        return rv;
    }
    public int largestBSTSubtree(TreeNode root) {
        findlargestBSTSubtree(root);
        return this.size;
    }
}