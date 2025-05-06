class Solution:
    # @param {string} s
    # @return {integer}
    def lengthOfLastWord(self, s):
        lis = s.rsplit(None,1);
        if not lis:
            return 0;
        return len(lis[-1]);