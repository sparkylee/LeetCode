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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> tnLinked =new LinkedList<>();
            tnNode head = new tnNode(root);
            while(head!=null&&head.val!=null)
            {
                list.add(head.val.val);
                tnNode l=null,r = null,pHead = null;

                TreeNode val = head.val;
                head = head.next;
                if(val.right!=null) {
                    r = new tnNode(val.right);
                    r.next = head;
                    head = r;
                }
                if(val.left!=null)
                {
                    l = new tnNode(val.left);
                    l.next = head;
                    head = l;
                }
            }
            return list;
        }
}