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
      public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int sumLeft = 0, sumRight = 0, sum = 0;
        if (L < root.val)
            sumLeft = rangeSumBST(root.left, L, R);
        if (R > root.val)
            sumRight = rangeSumBST(root.right, L, R);
        if (L <= root.val && R >= root.val)
            sum += root.val;
        return sum + sumLeft + sumRight;
    }
}