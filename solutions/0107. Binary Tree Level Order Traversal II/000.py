# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        allList   = [];
        levellist = [];
        nrootlist = [];
        rootlist  = [];
        if(root!=None):
            rootlist.append(root);
        while(rootlist):
            for root in rootlist:
                if root != None:
                    levellist.append(root.val);
                    if root.left != None:
                        nrootlist.append(root.left);
                    if root.right != None:
                        nrootlist.append(root.right);
            allList = [levellist] + allList;
            levellist = [];
            rootlist = nrootlist;
            nrootlist = [];
        return allList;
        
        