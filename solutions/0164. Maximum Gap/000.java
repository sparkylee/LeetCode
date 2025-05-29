class Solution {
    int harmonizeNums(int [] nums) {
        int base = nums[0];
        for(int i=0; i < nums.length; i++){
            base = nums[i] < base ? nums[i] : base;
        }
        for(int i=0; i < nums.length; i++){
            nums[i] = nums[i] - base;
        }
        int max_value = nums[0];
        for(int i=0; i < nums.length; i++){
            max_value = nums[i] > max_value ? nums[i] : max_value;
        }
        return max_value;
    }
    public int maximumGap(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        for(int i=0; i < nums.length; i++){
            numSet.add(nums[i]);
        }
        nums = new int[numSet.size()];
        int c = 0;
        for(int x : numSet) 
            nums[c++] = x;
        if (nums.length <=1) {
            return 0;
        }
        int max_value = harmonizeNums(nums);
        int session_count = nums.length - 1;
        int max_element_count = (max_value + 1);
        int session_size = max_element_count / session_count + 1; 
        int [][] groups =  new int[session_count+1][4];
        for(int i=0; i< nums.length; i++) {
            int num = nums[i];
            int gi = num / session_size;
            if(groups[gi][0]==0) {
                groups[gi][0] = 1;
                groups[gi][1] = num;
                groups[gi][2] = num;
            } else 
            {
                groups[gi][1] = Math.min(groups[gi][1], num);
                groups[gi][2] = Math.max(groups[gi][2], num);
            }
            groups[gi][3] ++;
        }
        int last_max = 0;
        int max_gap = 0;
        for(int i = 0; i < groups.length; i++) {
            int [] group = groups[i];
            if(group[0]==1) {
                int gap = group[1] - last_max;
                max_gap = Math.max(max_gap, gap);
                if( group[3]==2)
                {
                    gap = Math.abs( group[1] -  group[2]);
                    max_gap = Math.max(max_gap, gap);
                }
                last_max = group[2];
            }
        }
        return max_gap;
    }
}