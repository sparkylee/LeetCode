# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> Optional[TreeNode]:
        if not root: return
        curr = root
        ans = None
        while curr:
            if curr.val > p.val:
                ans = curr
                curr = curr.left
            elif curr.val < p.val: curr = curr.right
            else:
                if curr.right:
                    nxt = curr.right
                    while nxt.left:
                        nxt = nxt.left
                    ans = nxt
                break
        return ans