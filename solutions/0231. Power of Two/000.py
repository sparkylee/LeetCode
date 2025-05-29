class Solution(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        while (n != 0) and (n != -1):
            if (n & 0x1) == 1:
                if n == 1:
                    return True;
                else:
                    return False;
            else:
                n = n >> 1;
        return False;