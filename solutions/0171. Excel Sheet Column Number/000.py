class Solution(object):
    def titleToNumber(self, s):
        """
        :type s: str
        :rtype: int
        """
        n = 0;
        for i in range(len(s)): 
            n = n*26+ (ord(s[i])-64);
        return n;