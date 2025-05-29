class Solution:
    # @param {integer[]} nums
    # @return {boolean}
    def containsDuplicate(self, nums):
        if not nums or len(nums) == 1:
            return False;
        nums_tmp = sorted(nums);
        for i in range(len(nums_tmp) - 1):
            if nums_tmp[i] == nums_tmp[i+1]:
                return True;
        return False;
            
        