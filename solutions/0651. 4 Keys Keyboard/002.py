class Solution:
    def maxA(self, n: int) -> int:
        dp = list(range(n+1))
        for i in range(6, n+1):
            for j in range(4, i-2): #i-2, i-1 are for select and copy, respectively. so j ends at i-3.
                dp[i] = max(dp[i], dp[j-1]*(i-j)) #d[j-1] is the entire A ready for select, copy, and paste.
                                                    #between i and j, there are i-j+1 element, minus 2 for select and copy,
                                                    # so the number of paste is i-j-1. since there is already a selected string,
                                                    # so times i-j-1 +1 = i-j
        return dp[-1]