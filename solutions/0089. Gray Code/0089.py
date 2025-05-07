class Solution(object):
    def grayCode(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        seq = [0]
        for i in range(n):
            v = 1<<i;
            num = len(seq);
            for j in reversed(range(num)):
                seq.append(v+seq[j]);
        return seq;