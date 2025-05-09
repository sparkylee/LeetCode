class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def majorityElement(self, nums):
        nums_tmp = sorted(nums);
        count = 1;
        length  = len(nums_tmp);
        count_th = length // 2;
        if length % 2 == 1:
            count_th += 1;
        if length == 1 :
            return nums_tmp[0];
        for i in range(length-1):
            if nums_tmp[i] == nums_tmp[i+1]:
                count += 1;
            else:
                count = 1;
            if count >= count_th:
                return nums_tmp[i];