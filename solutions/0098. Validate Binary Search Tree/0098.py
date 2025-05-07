# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isValidBSTRecursive(self,root):
        if not root:
            return True,None,None;
        lb,lmin,lmax = self.isValidBSTRecursive(root.left);
        if not lb :
            return False,None,None;
        rb, rmin,rmax = self.isValidBSTRecursive(root.right);
        if not rb:
            return False,None,None;
        if (not lmax or root.val > lmax) and (not rmin or root.val < rmin):
            min_v = lmin if lmin else root.val;
            max_v = rmax if rmax else root.val;
            return True,min_v,max_v;
        return False,None,None;
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        bv,min_v,max_v = self.isValidBSTRecursive(root);
        return bv;