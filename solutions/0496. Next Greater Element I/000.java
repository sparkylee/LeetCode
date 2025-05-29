class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
         int [] results = new int[nums1.length];
            for(int i=0;i<nums1.length;i++)
            {
                boolean isSelfFound = false;
                results[i] = -1;
                for(int j=0;j<nums2.length;j++)
                {
                    if(nums2[j]==nums1[i])
                    {
                        isSelfFound = true;
                        continue;
                    }
                    if(isSelfFound && nums2[j]>nums1[i]) {
                        results[i] = nums2[j];
                        break;
                    }
                }
            }
            return results;
    }
}