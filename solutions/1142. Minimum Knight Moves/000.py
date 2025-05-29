class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        memo= {}
        def dp(x, y):
            if x==0 and y==0: return 0
            elif x+y ==2: return 2
            if (x, y) in memo: return memo[(x, y)]
            else:
                memo[(x, y)] =  min(dp(abs(x-1), abs(y-2)), dp(abs(x-2), abs(y-1)))+1
                return memo[(x, y)]
        return dp(abs(x), abs(y))