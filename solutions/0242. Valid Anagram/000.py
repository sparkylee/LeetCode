class Solution:
    # @param {string} s
    # @param {string} t
    # @return {boolean}
    def isAnagram(self, s, t):
        s_array = [0]*26;
        t_array = [0]*26;
        for c in s:
            s_array[ord(c)-97] += 1;
        for c in t:            
            t_array[ord(c)-97] += 1;
            
        for i in range(26):
            if s_array[i] != t_array[i]:
                return False;            
        return True;