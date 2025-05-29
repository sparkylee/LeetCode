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
     private void appendNode(StringBuilder parent, TreeNode node)
    {
        parent.append('(');
        StringBuilder sbx = tree2strRe(node);
        if(sbx!=null) parent.append(sbx);
        parent.append(')');
    }
    public StringBuilder tree2strRe(TreeNode t) {
        if(t==null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        if(t.left!=null || t.right!=null) appendNode(sb, t.left);
        if(t.right!=null) appendNode(sb, t.right);
        return sb;
    }
    public String tree2str(TreeNode t) {
       if(t==null) return "";
        return tree2strRe(t).toString();
    }
}