class Solution:
    def verifyPreorder(self, preorder: List[int]) -> bool:
        rootval = float('-inf')
        stack = []
        for i in range(len(preorder)):
            while stack and stack[-1]< preorder[i]:
                rootval = stack.pop()
            if preorder[i]< rootval: return False
            stack.append(preorder[i])
        return True