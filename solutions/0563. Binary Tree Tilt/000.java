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
    private int calcSum(TreeNode root) {
        if (root == null) return 0;
        int val = calcSum(root.left);
        val += root.val;
        val += calcSum(root.right);
        root.val = val;
        return val;
    }

    private int getVal(TreeNode node) {
        if (node == null) return 0;
        return node.val;
    }

    private int calcTilt(TreeNode root) {
        if (root == null) return 0;
        int tiltSum = calcTilt(root.left);
        int tiltRoot = Math.abs(getVal(root.left) - getVal(root.right));
        tiltSum += tiltRoot;
        tiltSum += calcTilt(root.right);
        return tiltSum;
    }

    public int findTilt(TreeNode root) {
        calcSum(root);
        return calcTilt(root);
    }

}