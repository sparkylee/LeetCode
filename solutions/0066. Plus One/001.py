class Solution:
    # @param {integer[]} digits
    # @return {integer[]}
    def plusOne(self, digits):
        cin = 1;
        for i in reversed(range(len(digits))):
            tmp  =  digits[i] + cin ;
            cin = tmp // 10;
            digits[i] = tmp % 10;
        if cin !=0:
            digits.insert(0,cin);
        return digits;