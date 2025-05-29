# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    
    def levelOrderTraverse(self,root,depth,LL):
        if root == None:
            return;
        if depth >= len(LL) :
            LL.append([]);
        LL[depth].append(root.val);
        self.levelOrderTraverse(root.left, depth + 1, LL);
        self.levelOrderTraverse(root.right,depth + 1, LL);
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        LL = [];
        self.levelOrderTraverse(root,0,LL);
        return LL;
        