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
    Map<TreeNode, Integer> map;
    int rob(TreeNode root,   Map<TreeNode, Integer> map) {
      if(root==null)
        return 0;
      if(map.containsKey(root))
        return map.get(root);
      int leftValue = rob(root.left, map);
      int rightValue = rob(root.right, map);
      int value1 = leftValue + rightValue;
      leftValue = 0;
      if(root.left!=null) {
        leftValue = rob(root.left.left, map) +  rob(root.left.right, map);
      }
      rightValue = 0;
      if(root.right!=null) {
        rightValue = rob(root.right.left, map) +  rob(root.right.right, map);
      }
      int value2 =  root.val + leftValue + rightValue;
      int valueMax = Math.max(value1, value2);
      map.put(root, valueMax);
      return valueMax;
      
    }
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        rob(root,map);
        return map.getOrDefault(root, 0);
    }
}