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
    int value;
    double min_d;
    boolean isFirstStep = true;
    public int closestValue(TreeNode root, double target) {
        if(root==null)
        {
             return value;
        }
        double delta = root.val - target;
        if(delta==0.0) {
            value = root.val;
            min_d = 0.0;
            isFirstStep = false;
            return value;
        }
        double delta_abs = Math.abs(delta);
        if(isFirstStep || delta_abs < Math.abs(min_d) || (delta_abs == Math.abs(min_d) && root.val < value) ){
            value = root.val;
            min_d = delta;
            isFirstStep = false;
        }
        if(delta < 0) {
            return closestValue(root.right, target);
        } else {
            return closestValue(root.left, target);
        }
    }
}