# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isMirrorEq(self,r1,r2):
        if r1 == None and r2 == None:
            return True;
        if r1 == None or r2 == None:
            return False;
        if r1.val != r2.val:
            return False;
        return self.isMirrorEq(r1.left,r2.right) and self.isMirrorEq(r1.right,r2.left);
    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root == None:
            return True;
        return self.isMirrorEq(root.left,root.right);

