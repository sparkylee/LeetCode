class Solution(object):
    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        m  = 0;
        while n != 0:
            m = m + (n & 0x1);
            n = n >> 1;
        return m;