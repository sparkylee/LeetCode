class Solution {
     private  void enforceInplace(int [] nums,int i)
        {
            while(true) {
                if(nums[i]<0 || nums[i]==i+1) return;
                int j = nums[i]-1;
                if(nums[j]==nums[i])
                    nums[i] = -1;
                else
                {
                    int tmp = nums[j];
                    nums[j]=nums[i];
                    nums[i] = tmp;
                }
            }
        }
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for(int i=0;i<nums.length;i++)
                enforceInplace(nums,i);
            List<Integer> nums_n = new ArrayList<>();
            for(int i=0;i<nums.length;i++)
            {
                if(nums[i] < 0 ) nums_n.add(i+1);
            }
            return nums_n;
        }
}