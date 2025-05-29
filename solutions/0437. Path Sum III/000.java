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
        public int pathSum(TreeNode root, int sum,boolean headSelected) {
            if(root==null) return 0;
            int count = 0;
            if(root.val==sum)
                count = 1;
            int residue = sum - root.val;
            count += pathSum(root.left,residue,true);
            count += pathSum(root.right,residue,true);
            if(!headSelected) {
                count += pathSum(root.left,sum,false);
                count += pathSum(root.right,sum,false);
            }
            return count;
        }
    public int pathSum(TreeNode root, int sum) {
         return pathSum(root,sum,false);
    }
}