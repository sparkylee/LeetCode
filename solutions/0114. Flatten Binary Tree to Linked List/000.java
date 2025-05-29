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
   private TreeNode flattenRecursive(TreeNode root)
        {
            if(root==null) return null;
            TreeNode root_tail   = root;
            TreeNode left_tail  = flattenRecursive(root.left);
            TreeNode right_tail = flattenRecursive(root.right);
            TreeNode tmp = root.right;
            if(root.left!=null)
            {
                root.right = root.left;
                root.left = null;
                root_tail = left_tail;

            }
            root_tail.right = tmp;
            if(tmp!=null)
            {
                root_tail = right_tail;
            }
            return root_tail;
        }

        public void flatten(TreeNode root) {
            flattenRecursive(root);
        }
}