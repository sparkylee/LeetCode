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
    private void maxLevelSum(TreeNode root, int k, List<Integer> results) {
        if(root==null) return;
        if(k>=results.size()) results.add(0);
        results.set(k, results.get(k) + root.val);
        maxLevelSum(root.left, k+1, results);
        maxLevelSum(root.right, k+1, results);
    }
    public int maxLevelSum(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        maxLevelSum(root, 0, results);
        int im =0;
        int ma = results.get(0);
        for(int i=0;i<results.size();i++){
            if(ma<results.get(i)) {
                im = i;
                ma = results.get(i);
            }
        }
        return im+1;
    }
}