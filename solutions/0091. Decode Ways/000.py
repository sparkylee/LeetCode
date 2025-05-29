class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 0;
        buf = [0]*len(s);
        if s[-1] != '0':
            buf[-1] = 1;
        for i in reversed(range(len(s)-1)):
            if i + 2 < len(s):
                n2 = buf[i+2];
            else:
                n2 = 1;
            if s[i] == '0':
                buf[i] = 0;
                continue;
            elif s[i+1] == '0':
                if s[i] == '1' or s[i] == '2':
                    buf[i] = n2;
                    continue;
                else:
                    return 0;
            else:
                if s[i] >= '3' or (s[i] == '2' and s[i+1] >= '7'):
                    buf[i] = buf[i+1];
                else: #s[i] == '1' or '2':
                    buf[i] = buf[i+1] + n2;
        return buf[0];
        