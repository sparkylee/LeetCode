/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    private TreeNode sortedListToBSTRecursive(List<ListNode> nodes,int start,int end) {
            int len = end - start + 1;
            if(len<=0) return null;
            int root_index = (start+end)/2;
            TreeNode root = new TreeNode(nodes.get(root_index).val);
            TreeNode ln = sortedListToBSTRecursive(nodes,start,root_index-1);
            TreeNode rn = sortedListToBSTRecursive(nodes,root_index+1,end);
            root.left = ln;
            root.right = rn;
            return root;
        }
        public TreeNode sortedListToBST(ListNode head) {
            List<ListNode> nodeList = new ArrayList<>();
            while(head!=null) {
                nodeList.add(head);
                head=head.next;
            }
            return  sortedListToBSTRecursive(nodeList,0,nodeList.size()-1);
        }
}