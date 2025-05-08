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
    private TreeNode buildTreeRecursive(int[] preorder,int pstart,int pend, int[] inorder,int istart,int iend) {
        if(pstart > pend  || pstart>= preorder.length )
            return null;
        int root = preorder[pstart];
        TreeNode node = new TreeNode(root);
        int lcount = 0;
        for(int i= istart;i<=iend;i++)
        {
            if(inorder[i]==root) break;
            lcount++;
        }
        TreeNode lnode= buildTreeRecursive(preorder,pstart+1,pstart+lcount,inorder,istart,istart+lcount);
        TreeNode rnode= buildTreeRecursive(preorder,pstart+lcount+1,pend,inorder,istart+lcount+1,iend);
        node.left  = lnode;
        node.right = rnode;
        return node;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(inorder.length < 1 || preorder.length != inorder.length)
            return null;
        return buildTreeRecursive(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
}