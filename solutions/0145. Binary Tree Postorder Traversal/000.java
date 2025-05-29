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
    class tnNode
        {
            TreeNode val;
            tnNode next;
            tnNode(TreeNode val)
            {
                this.val = val;
                this.next =null;
            }
        }
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            tnNode head = new tnNode(root);
            while(head!=null&&head.val!=null)
            {
                tnNode l=null,r = null;
                TreeNode val = head.val;
                if(val.left==null && val.right==null) {
                    list.add(head.val.val);
                    head = head.next;
                }
                if(val.right!=null) {
                    r = new tnNode(val.right);
                    val.right = null;
                    r.next = head;
                    head = r;
                }
                if(val.left!=null)
                {
                    l = new tnNode(val.left);
                    val.left = null;
                    l.next = head;
                    head = l;
                }

            }
            return list;
        }
}