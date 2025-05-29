# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    
    def pathSumSearch(self,root,sum,candidate):        
        candidate.append(root.val);
        newSum = sum -root.val;
        if root.left:
            self.pathSumSearch(root.left,newSum,candidate);
        if root.right:
            self.pathSumSearch(root.right,newSum,candidate);
        if (not root.left) and (not root.right) and newSum==0:
            self.result.append(list(candidate));
        del candidate[-1];
        return;
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """
        
            
        self.result = [];
        if root:            
            candidate = []            
            self.pathSumSearch(root,sum,candidate);
        return self.result;
        
        