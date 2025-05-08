class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        i = 0;
        j = 0;
        n = len(prices);
        if n == 1 or n == 0:
            return 0;
        p = prices[j] - prices[i];
        for k in range(1,n):
            if prices[k] < prices[i]:
                i = k;
                j = k;
            elif prices[k] > prices[j]:
                j = k;
                p1 = prices[j] - prices[i];
                if p1 > p:
                    p = p1;
        return p;
