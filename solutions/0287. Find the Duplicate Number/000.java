class Solution {
    public int findDuplicate(int[] nums) {
            int n = nums.length-1;
            int n_index = n-1;
            int len = (n_index + 64) /64;
            long [] bitArray = new long[len];
            long bits = 0;
            for(int i=0;i<nums.length;i++)
            {
                int j =  (nums[i] - 1)/64;
                bits = bitArray[j];
                int k = (nums[i] - 1)%64;
                long value = (bits>>k) & 0x1;
                if(value!=0)
                    return nums[i];
                bitArray[j] = bitArray[j] | (((long)1)<<k);
            }
            return 0;
        }
}