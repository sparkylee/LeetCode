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
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTRecursive(nums,0,nums.length-1);
    }
    private TreeNode sortedArrayToBSTRecursive(int[] nums,int start, int end) {
        if(start>end) return null;
        if(start==end) return new TreeNode(nums[start]);
        int len = end - start + 1;
        int root  = (start + end + 1) /2 ;
        TreeNode left  = sortedArrayToBSTRecursive(nums,start,root -1);
        TreeNode right  = sortedArrayToBSTRecursive(nums,root+1,end);
        TreeNode rootNode = new TreeNode(nums[root]);
        rootNode.left  = left;
        rootNode.right = right;
        return rootNode;
    }
}