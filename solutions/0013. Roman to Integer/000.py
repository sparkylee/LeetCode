class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        nums = [None]*len(s);
        for i in range(len(s)): 
            if s[i] == "I":
                nums[i] = 1;
            elif s[i] == "V":
                nums[i] = 5;
            elif s[i] == "X":
                nums[i] = 10;
            elif s[i] == "L":
                nums[i] = 50;
            elif s[i] == "C":
                nums[i] = 100;
            elif s[i] == "D":
                nums[i] = 500;
            elif s[i] == "M":
                nums[i] = 1000;
        n = 0;
        for i in range(len(nums)): 
            if i == (len(nums) - 1):
                n = nums[i] + n;
                continue;
            if nums[i] < nums[i+1]:
                n = n - nums[i];
                continue;
            n = nums[i] + n;
            
        return n;
        
        
        
        
        