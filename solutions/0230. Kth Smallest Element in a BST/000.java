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
    boolean found = false;
    int result = 0;
    int count = 0;
    void countLeft(TreeNode root, int k)
    {
        if(this.found || root==null)
            return ;
        countLeft(root.left, k);
        this.count ++;
        if(this.count==k)
        {
            this.found = true;
            this.result = root.val;
            return ;
        }
        countLeft(root.right, k);
        return;
    }
    public int kthSmallest(TreeNode root, int k) {
        countLeft(root,k);
        return this.result;
    }
}