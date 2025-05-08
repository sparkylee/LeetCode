/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    private TreeLinkNode getFirstChild(TreeLinkNode head)
    {
        while(head!=null)
        {
            if(head.left!=null) return head.left;
            if(head.right!=null) return head.right;
            head = head.next;
        }
        return null;
    }
    public void connect(TreeLinkNode root) {
        if(root==null ) return;
        TreeLinkNode pp = root, head = getFirstChild(root);
        while (head!=null)
        {
            TreeLinkNode p = head;
            while (pp!=null) {
                if(pp.left!=null && p!=null && pp.left!=p) {
                    p.next = pp.left;
                    p = p.next;
                }
                if(pp.right!=null && p!=null && pp.right!=p) {
                    p.next = pp.right;
                    p = p.next;
                }
                pp=pp.next;
            }
            pp = head;
            head = getFirstChild(head);
        }
    }
}