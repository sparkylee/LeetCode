class Solution(object):
    def isPowerOfFour(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if (num & 0xaaaaaaaa) != 0:
            return False;
        N = 0;
        for i in range(16):
            if ( (num >> (i*2) ) & 1) == 1:
                N = N + 1;
        return N==1;