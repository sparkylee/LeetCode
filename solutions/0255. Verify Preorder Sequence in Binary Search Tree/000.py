class Solution:
    def verifyPreorder(self, preorder: List[int]) -> bool:
        i=0
        size = len(preorder)
        def dfs(mini, maxi):
            nonlocal i
            if i>=size: return True
            rootval = preorder[i]
            if not mini<rootval<maxi: return False            
            i+=1
            return dfs(mini, rootval) or  dfs(rootval, maxi)
            
        return dfs(float('-inf'), float('inf'))