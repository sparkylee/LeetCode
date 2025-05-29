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
      TreeNode trimRoot(TreeNode root, int L, int R) {
        while (root != null) {
            if (root.val < L) {
                root = root.right;
                continue;
            }
            if (root.val > R) {
                root = root.left;
                continue;
            }
            return root;
        }
        return null;
    }

    void trimLeft(TreeNode root, int L) {
        while (root != null) {
            while (root.left != null && root.left.val < L) {
                root.left = root.left.right;
            }
            root = root.left;
        }
    }

    void trimRight(TreeNode root, int R) {
        while (root != null) {
            while (root.right != null && root.right.val > R) {
                root.right = root.right.left;
            }
            root = root.right;
        }
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        root = trimRoot(root, L, R);
        trimLeft(root, L);
        trimRight(root, R);
        return root;
    }
}