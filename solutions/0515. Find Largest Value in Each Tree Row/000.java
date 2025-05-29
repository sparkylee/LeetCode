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
   public void largestValues(TreeNode root, int k, List<Integer> results) {
        if (root == null) return;
        if (k >= results.size())
            results.add(root.val);
        else {
            Integer v = results.get(k);
            if (root.val > v)
                results.set(k, root.val);
        }
        largestValues(root.left, k + 1, results);
        largestValues(root.right, k + 1, results);
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        largestValues(root, 0, results);
        return results;
    }
}