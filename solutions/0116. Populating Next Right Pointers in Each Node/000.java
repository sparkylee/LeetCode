/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
           if(root==null || root.left==null) return;
            TreeLinkNode pp = root, head = root.left;
            while (head!=null)
            {
                TreeLinkNode p = head;
                boolean isOdd = true;
                while (pp!=null) {
                    p.next = isOdd?  pp.right : pp.left;
                    if(isOdd) pp = pp.next;
                    p = p.next;
                    isOdd = !isOdd;
                }
                pp = head;
                head = head.left;
            }
    }
}