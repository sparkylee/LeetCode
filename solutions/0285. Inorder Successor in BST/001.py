# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> Optional[TreeNode]:
        def inorder(node):
            nonlocal ans
            if node is None or ans: return
            inorder(node.left)
            if node.val >p.val and not ans:
                ans = node
                return
            inorder(node.right)
        ans = None
        inorder(root)
        return ans