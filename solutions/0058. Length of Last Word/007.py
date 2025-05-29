class Solution:
    # @param {string} s
    # @return {integer}
    def lengthOfLastWord(self, s):
        length = len(s);
        if length == 0:
            return 0;
        pos_end = length -1;
        while pos_end >= 0:        
            if s[pos_end].isspace() == False:
                break;
            else:
                pos_end = pos_end - 1;
        if pos_end < 0 :
            return 0;
        pos_front = pos_end-1;
        while pos_front >= 0:        
            if  s[pos_front].isspace():
                break;
            else:
                pos_front = pos_front - 1;
                             
        return pos_end - pos_front;