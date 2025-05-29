class Solution:
    # @param {string} s
    # @return {integer}
    def lengthOfLastWord(self, s):
        s_tmp = s.rstrip();
        length = len(s_tmp);
        if length == 0:
            return 0;
        pos_front = length-1;
        while pos_front >= 0:        
            if  s[pos_front].isspace():
                break;
            else:
                pos_front = pos_front - 1;
                             
        return (length -1 - pos_front);