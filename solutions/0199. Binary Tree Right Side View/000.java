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
     int height = -1;
        private void rightSideView(TreeNode root,List<Integer> l, int h)
        {
            if(root==null) return ;
            if(h>this.height)
            {
                this.height=h;
                l.add(root.val);
            }
            rightSideView(root.right,l,h+1);
            rightSideView(root.left,l,h+1);
        }
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> l = new ArrayList<>();
            rightSideView(root,l,0);
            return l;
        }
}