class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """

        PT = [];

        if numRows <= 0:
            return PT;
        for i in range(numRows):
            PTrow = [];
            if i == 0:
                PTrow.append(1);
            else:
                for j in range(0,i+1):
                    if j == 0:
                        PTrow.append(0 + PT[i-1][j]);
                    elif j == i:
                        PTrow.append(PT[i-1][j-1] + 0);
                    else:
                        PTrow.append(PT[i-1][j-1]+PT[i-1][j]);
            PT.append(PTrow);
        return PT;
