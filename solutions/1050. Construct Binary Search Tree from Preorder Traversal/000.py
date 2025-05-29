# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        mini, maxi = float('-inf'), float('inf')
        i=0
        size = len(preorder)
        def dfs(mini, maxi):
            nonlocal i
            if i>=size: return
            val = preorder[i]
            if not mini<val<maxi: return
            root = TreeNode(val)
            i+=1
            root.left = dfs(mini, val)
            root.right = dfs(val, maxi)
            return root
        return dfs(mini, maxi)        