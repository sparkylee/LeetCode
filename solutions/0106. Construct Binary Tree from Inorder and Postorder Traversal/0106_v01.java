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
    private TreeNode buildTreeRecursive(int[] inorder, int istart,int iend,int[] postorder,int pstart,int pend) {
        if(pend<0 || pend >=postorder.length || istart<0||istart>=inorder.length
                || iend<istart || pend<pstart) return null;
        int root = postorder[pend];
        int k= 0 ;
        while(k<iend-istart+1) {
            if (inorder[k + istart] == root) break;
            k++;
        }

        TreeNode rootNode = new TreeNode(root);
        TreeNode leftNode = buildTreeRecursive(inorder,istart,istart+k-1,postorder,pstart,pstart+k-1);
        TreeNode rightNode = buildTreeRecursive(inorder,istart+k+1,iend,postorder,pstart+k,pend-1);
        rootNode.left  = leftNode;
        rootNode.right = rightNode;
        return  rootNode;
    }
    public  TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeRecursive(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }
}