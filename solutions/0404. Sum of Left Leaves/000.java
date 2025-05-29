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
    private int sumOfLeftLeavesRecursive(TreeNode node,boolean isLeft,int sum)
        {
          if(node==null) return sum;
            if(node.left==null && node.right==null)
            {
                if(isLeft) sum+=node.val;
                return sum;
            }
            if(node.left!=null)
                sum = sumOfLeftLeavesRecursive(node.left,true,sum);
            if(node.right!=null)
                sum = sumOfLeftLeavesRecursive(node.right,false,sum);
            return sum;
        }
        public int sumOfLeftLeaves(TreeNode root) {
            int sum = sumOfLeftLeavesRecursive(root,false,0);
            return sum;
        }
}