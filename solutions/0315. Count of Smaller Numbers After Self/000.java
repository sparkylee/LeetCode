class Solution {
    public List<Integer> countSmaller(int[] nums) {
         List<Integer> result = new ArrayList<>();
            for(int i=0;i<nums.length;i++)
            {
                int count = 0;
                int ni=nums[i];
                for(int j=i+1;j<nums.length;j++)
                {
                    if(nums[j]<ni) count++;
                }
                result.add(count);
            }
            return result;
        
    }
}