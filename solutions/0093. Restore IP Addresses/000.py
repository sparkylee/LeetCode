
class Solution(object):
    def recurConvertIP(self,i,candidate):
        if i >= self.n or len(candidate) >= 4:
            if  i>= self.n and len(candidate) == 4:
                self.result.append('.'.join(candidate));
            return;
        for j in range(0,3):
            if i+j+1 > self.n:
                return;
            if j==2 and self.D3L[i] > 255:
                return;
            candidate.append(self.s[i:i+j+1]);
            self.recurConvertIP(i+j+1,candidate);
            del candidate[-1:];
            if j==0 and self.s[i] == '0':
                return;
        return;
    def restoreIpAddresses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        self.result = [];
        self.s = s;
        self.n = len(self.s);
        self.D3L  = [0]*self.n;
        for i in range(self.n):
            d0 = (int(self.s[i]) - int('0'))*100;
            d1 = 0 if i+1 >= self.n  else int(int(self.s[i+1]) - int('0'))*10;
            d2 = 0 if i + 2 >= self.n  else int(int(self.s[i + 2]) - int('0')) ;
            self.D3L[i] = d0+d1+d2;
        candidate = [];
        self.recurConvertIP(0,candidate);
        return self.result;