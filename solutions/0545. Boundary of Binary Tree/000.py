# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def boundaryOfBinaryTree(self, root: Optional[TreeNode]) -> List[int]:
        def isleaf(curr):
            return curr.left is None and curr.right is None
        #inorder
        def left(curr, ans):
            if not curr or isleaf(curr): return
            ans.append(curr.val)
            if curr.left:
                left(curr.left, ans)
            else:
                left(curr.right, ans)
        def leaves(curr, ans):
            if not curr: return
            if isleaf(curr):
                ans.append(curr.val)
            leaves(curr.left, ans)
            leaves(curr.right, ans)

        def right(curr, ans):
            if not curr or isleaf(curr): return
            if curr.right:
                right(curr.right, ans)
            else:
                right(curr.left, ans)
            ans.append(curr.val)
        ans = []
        if not root: return []
        if not isleaf(root): ans= [root.val]
        left(root.left, ans)
        leaves(root, ans)
        right(root.right, ans)
        return ans