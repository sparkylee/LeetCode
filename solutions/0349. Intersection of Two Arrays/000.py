class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums3 = []
        for i in nums1:
            for j in nums2:
                if i == j:
                    inTag = False;
                    for k in nums3:
                        if k == i:
                            inTag = True;
                    if inTag == False:
                        nums3.append(i);
            
        return nums3;