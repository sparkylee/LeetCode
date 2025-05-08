# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def setTreeHeight(self, root):
        if root == None:
            return 0;
        else:
            root.val = max(self.setTreeHeight(root.left),self.setTreeHeight(root.right)) + 1;
            return root.val;

    def getTreeHeight(self, root):
        if root == None:
            return 0;
        else:
            return root.val;

    def checkBalanced(self, root):
        if root == None:
            return True;
        return abs(self.getTreeHeight(root.left)-self.getTreeHeight(root.right)) <= 1 and self.checkBalanced(root.left) and self.checkBalanced(root.right);

    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        self.setTreeHeight(root);
        return self.checkBalanced(root);





