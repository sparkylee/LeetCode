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
    int ms=0;
    boolean msSet = false;
    public int maxPath(TreeNode root)
    {
        if(root==null)
            return -1;
        int leftSum = maxPath(root.left),rightSum = maxPath(root.right);
        int childSumMax = leftSum>rightSum?leftSum:rightSum,childSumMin = leftSum<rightSum?leftSum:rightSum;
        int pathSumMax = childSumMax>0?(root.val+childSumMax):root.val;
        int localPathSumMax = childSumMin>0?pathSumMax+childSumMin:pathSumMax;
        if(localPathSumMax>ms || !msSet)
        {
            ms = localPathSumMax;
            msSet = true;
        }
        return pathSumMax;
    }
    public int maxPathSum(TreeNode root)
    {
        int pathSumMax = maxPath(root);
        return this.ms;
    }
}