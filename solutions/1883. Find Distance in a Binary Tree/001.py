# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findDistance(self, root: Optional[TreeNode], p: int, q: int) -> int:
        def lca(curr, p, q):
            if curr is None: return
            if curr.val == p or curr.val == q: return curr
            left = lca(curr.left, p, q)
            right = lca(curr.right, p, q)
            if left and right: return curr
            return left if left else right

        lcanode = lca(root, p, q)
        def distance(obj, curr, level):
            if curr is None: return -1
            if curr.val==obj: return level
            leftlevel = distance(obj, curr.left, level+1)
            if leftlevel != -1: return leftlevel
            else: return distance(obj, curr.right, level+1)
        levelp= distance(p, lcanode, 0)
        levelq = distance(q, lcanode, 0)
        return levelp + levelq