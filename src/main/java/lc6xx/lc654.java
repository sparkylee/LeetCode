package lc6xx;

import org.junit.Test;

public class lc654 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end || start < 0 || end >= nums.length) return null;
        int m = nums[start];
        int im = start;
        for (int i = start; i <= end; i++) {
            if (m < nums[i]) {
                m = nums[i];
                im = i;
            }
        }
        TreeNode left = constructMaximumBinaryTree(nums, start, im - 1);
        TreeNode right = constructMaximumBinaryTree(nums, im + 1, end);
        TreeNode root = new TreeNode(m);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }
}
