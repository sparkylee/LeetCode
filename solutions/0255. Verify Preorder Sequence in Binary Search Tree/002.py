class Solution:
    def verifyPreorder(self, preorder: List[int]) -> bool:
        rootval = float('-inf')
        stack = []
        for i in range(len(preorder)):
            if preorder[i]< rootval: return False
            while stack and stack[-1]< preorder[i]:
                rootval = stack.pop()
            stack.append(preorder[i])
        return True