class Solution:
    def maxA(self, n: int) -> int:
        dp = [i for i in range(n+1)]
        for i in range(6, n+1):
            dp[i]= max(dp[i-3]*2, dp[i-4]*3, dp[i-5]*4)
        return dp[-1]