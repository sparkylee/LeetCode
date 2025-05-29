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
        def left(curr, ans):
            if not curr: return
            while not isleaf(curr):
                ans.append(curr.val)
                if curr.left: curr = curr.left
                else: curr = curr.right

        def leaves(curr, ans):
            if curr is None:
                return
            if isleaf(curr):
                ans.append(curr.val)
                return
            leaves(curr.left, ans)
            leaves(curr.right, ans)

        def right(curr, ans):
            if not curr: return
            tmp = []
            while not isleaf(curr):
                tmp.append(curr.val)
                if curr.right: curr = curr.right
                else: curr = curr.left
            ans.extend(reversed(tmp))
        ans =[]
        if not root: return ans
        if not isleaf(root): ans =[root.val]
        left(root.left, ans)
        leaves(root, ans)
        right(root.right, ans)
        return ans