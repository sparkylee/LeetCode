class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        cin = 0;
        s = 0;
        for i in range(33):
            sum = 0;
            for item in nums:
                sum += (item >> i & 0x1);
            d = sum // 3;
            c = sum % 3;
            c1 = cin % 3;
            sbit = (c+3 - c1) % 3;
            s += (sbit << i) ;
        s = (s & 0xffffffff)
        if s & 0x80000000:
            return s - 0x100000000;
        return s;