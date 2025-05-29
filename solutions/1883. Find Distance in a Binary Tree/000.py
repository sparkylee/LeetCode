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
        queue = deque([lcanode])
        foundp, foundq = False, False
        distance, level = 0,  0
        while queue and not (foundp and foundq):
            size = len(queue)
            for i in range(size):
                curr = queue.popleft()
                if curr.val == p:
                    foundp = True
                    distance += level
                elif curr.val ==q:
                    foundq = True
                    distance+= level
                if foundp and foundq: return distance
                if curr.left: queue.append(curr.left)
                if curr.right:queue.append(curr.right)
            level +=1
        return distance