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
    boolean findLeaves(TreeNode root, List<Integer> lst) {
        if(root==null) {
            return false;
        }      
        if(root.left==null && root.right==null)
        {
            lst.add(root.val);
            return true;
        }
        if(findLeaves(root.left, lst)){
            root.left = null;            
        }
        if(findLeaves(root.right, lst)){
            root.right = null;
        }
        return false;

    }
    public List<List<Integer>> findLeaves(TreeNode root) {
         List<List<Integer>> results = new ArrayList<>();
         if(root==null)
            return results;
        List<Integer> lst;
         while(root.left!=null || root.right!=null) {
            lst = new ArrayList<>();
            findLeaves(root, lst);
            if(!lst.isEmpty()) {
                results.add(lst);
            }
         }
        lst = new ArrayList<>();
        lst.add(root.val);
        results.add(lst);
        return results;
    }
}