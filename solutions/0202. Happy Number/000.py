class Solution(object):
    def isHappy(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n <= 0:
            return False;
        if n< 10:
            if n == 1 or n == 7:
                return True;
            else:
                return False;
        else:
            m = 0;
            while n != 0:
                m = m + (n % 10)**2;
                n = n // 10;
            return self.isHappy(m);