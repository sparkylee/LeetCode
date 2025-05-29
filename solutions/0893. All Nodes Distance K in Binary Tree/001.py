# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        graph = defaultdict(list)
        def buildgraph(curr, parent):
            if curr and parent:
                graph[curr.val].append(parent.val)
                graph[parent.val].append(curr.val)
            if curr.left:
                buildgraph(curr.left, curr)
            if curr.right:
                buildgraph(curr.right, curr)
        buildgraph(root, None)
        ans = []
        visited = set([target.val])
        def dfs(curr, distance):
            if distance == k:
                ans.append(curr)
                return
            for neighbor in graph[curr]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    dfs(neighbor, distance+1)
        dfs(target.val, 0)
        return ans