class Solution {
    int getSmallerIndex(int[] nums, int start, int end, int target) {
        if(start>end)
            return -1;
        int index_middle = (start + end)/2;
        if(nums[index_middle] >= target) {
            return getSmallerIndex(nums,start,index_middle-1,target);
        }
        int index_right = getSmallerIndex(nums,index_middle+1, end,target);
        if(index_right>=0) {
            return index_right;
        }      
        return index_middle;
        
      
    }
    public int threeSumSmaller(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            nums[i] += 100;
        }
        target += 300;
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            int target_j = target - nums[i] ;
            if(target_j < 0)
            {
              return count;  
            };
            for(int j=i+1;j<nums.length;j++) {
                int target_k = target_j - nums[j];
                if(target_k < 0)
                {
                    break;
                }
                int k = getSmallerIndex(nums, j+1, nums.length-1, target_k);
                if(k<0) {
                    break;
                }
                // System.out.println("i="+i+" j="+j+" k="+k);
                count += (k - j);
            }
        }
        return count;
    }
}