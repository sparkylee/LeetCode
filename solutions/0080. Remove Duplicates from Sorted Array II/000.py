class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        preItem,counter,dup = None,0,0;
        for item in nums:
            if item == preItem and dup >= 1:
                continue;
            dup = 0 if item != preItem else dup+1;
            nums[counter],preItem = item,item;
            counter +=1;
        return counter;