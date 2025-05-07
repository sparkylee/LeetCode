class Solution(object):
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """

        if n <= 1:
            return 1;
        self.M = [0]*(n+1);
        self.M[1] = 1;
        for k in range(2,n+1):
            vk = 0;
            for c in range(1,k+1):
                l = c - 1;
                r = k - c;
                lv = 1 if l ==0 else self.M[l];
                rv = 1 if r ==0 else self.M[r];
                vk +=  lv*rv;
            self.M[k] = vk;
        return self.M[n];