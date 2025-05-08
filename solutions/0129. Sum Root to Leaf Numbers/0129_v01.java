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
    private int sumNumbersRecursive(TreeNode root,int r2l, int sum) {
        if(root==null) return sum;
        r2l = root.val+r2l*10;
        if(root.left!=null)
            sum = sumNumbersRecursive(root.left,r2l,sum);
        if(root.right!=null)
            sum = sumNumbersRecursive(root.right,r2l,sum);
        if(root.left==null && root.right==null)
            sum +=r2l;
        return sum;
    }
    public int sumNumbers(TreeNode root) {
        return sumNumbersRecursive(root,0,0);
    }
}