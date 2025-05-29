class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int [] sums = new int[nums.length];
        int sum = 0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i = 0; i<nums.length;i++) {
            sum += nums[i];
            sums[i] = sum;
            map.put(sum, i);
        }
        int lenMax = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums.length - i <lenMax)
                break;
            int preIndex = i - 1;
            int preSum = (preIndex >= 0 ) ? sums[preIndex] : 0;
            int expectSum = preSum + k;
            int j = map.getOrDefault(expectSum, preIndex);
            int lenNew = j - i + 1;
            lenMax = Math.max(lenMax, lenNew);
        }
        return lenMax;

    }
}