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
  int diff = -1;
    private Integer getMinimumDifference(TreeNode root, Integer lastValue) {
        if(root==null) {
            return lastValue;
        }
        Integer lastValueCandidate = getMinimumDifference(root.left,lastValue);
        lastValue = lastValueCandidate==null? lastValue : lastValueCandidate;
        if(lastValue!=null)
        {
            int absValue = Math.abs(lastValue-root.val);
            if(this.diff == -1)
                this.diff = absValue;
            else
                this.diff = Math.min(this.diff, absValue);
        }
        lastValue = root.val;
        return getMinimumDifference(root.right,lastValue);

    }
    public int getMinimumDifference(TreeNode root) {
        this.diff = -1;
        getMinimumDifference(root,null);
        return this.diff;
    }
}