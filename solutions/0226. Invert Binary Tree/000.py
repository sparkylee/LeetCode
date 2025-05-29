# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param {TreeNode} root
    # @return {TreeNode}
    def invertTree(self, root):
        if root == None:
           return None;
        pNode = root.left;
        root.left = self.invertTree(root.right);
        root.right = self.invertTree(pNode);
        return root;