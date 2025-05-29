class Solution:
    def maxA(self, n: int) -> int:
        dp = list(range(n+1))
        for i in range(6, n+1):
            for j in range(2, i-2): #i-2, i-1 are for select and copy, respectively. so j ends at i-3,ready for select,
                                    # j is called breakpoint .
                dp[i] = max(dp[i], dp[j]*(i-j-1)) #d[j] is the entire A ready for select, copy, and paste.
                                                    #between i and j+1, there are i-j element, minus 2 for select and copy,
                                                    # so the number of paste is i-j-2. since there is already a selected string,
                                                    # so times i-j-2 +1 = i-j-1
        return dp[-1]