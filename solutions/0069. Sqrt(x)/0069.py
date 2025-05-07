class Solution(object):

    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        a = x;
        b = 0;

        while True:
            y = ((a + b+1) // 2);
            if a==b:
                return a;
            y2 = y*y;
            if y2 > x:
                a = y-1;
            elif y2 == x:
                return y;
            else:
                b = y;