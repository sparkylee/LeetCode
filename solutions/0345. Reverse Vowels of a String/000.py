class Solution(object):
    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        sL = list(s);
        i = 0;
        j = len(sL) -1;
        vL = ('a','e', 'i', 'o', 'u','A','E', 'I', 'O', 'U');
        while i < j:
            if sL[i] not in vL:
                i = i + 1;
                continue;
            if sL[j] not in vL:
                j = j - 1;
                continue;
            c = sL[i];
            sL[i] = sL[j];
            sL[j] = c;
            i = i + 1;
            j = j - 1;
        return ''.join(sL);
            
                