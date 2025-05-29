/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int longestConsecutive(TreeNode root) {
        if(root==null) 
            return 0;
        int minV = root.val;
        int maxV = root.val;
        Stack<TreeNode> rstack =  new Stack();   
        List<TreeNode> rootsNew = new ArrayList<>();
        rstack.add(root);
        while(!rstack.empty()) {
            TreeNode r = rstack.pop();
            maxV = Math.max(r.val, maxV);
            if(r.left!=null) {
                if(r.left.val==r.val+1) {
                    rstack.add(r.left);
                } else {
                    rootsNew.add(r.left);
                }
            }
             if(r.right!=null) {
                if(r.right.val==r.val+1) {
                    rstack.add(r.right);
                } else {
                    rootsNew.add(r.right);
                }
            }
        }
        int len = maxV - minV + 1;
        for(int i=0;i<rootsNew.size();i++){
            int lenNew = longestConsecutive(rootsNew.get(i));
            len = Math.max(len, lenNew);
        }
        return len;
    }
}