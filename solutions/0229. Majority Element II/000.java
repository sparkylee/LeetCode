class Solution {
    int M = 3;
    public List<Integer> majorityElement(int[] nums) {      
        List<Integer> results = new ArrayList<>();    
        int N = nums.length/3;   
        int [] values = new int[this.M-1];
        int [] counts = new int[this.M-1];
        int pcount = 0;
        for(int i=0;i<nums.length;i++) {
            int val = nums[i];
            boolean val_matched = false;
            int  empty_spot = -1;
            for(int j=0;j<this.M-1;j++) {
                if(values[j]==val && counts[j]>0) {
                    counts[j]++;
                    val_matched = true;
                    break;                    
                }
                if(counts[j]==0) {
                    empty_spot = j;
                }
            }
            if(val_matched) {
                continue;
            }
            if(empty_spot==-1) {
                for(int j=0;j<this.M-1;j++) {
                    counts[j]--;
                }
                continue;
            }
            values[empty_spot] = val;
            counts[empty_spot] ++;            
        }
        for(int j=0;j<this.M-1;j++) {
            if(counts[j]>0) {
                int val = values[j];
                int count = 0;
                for(int i=0;i<nums.length;i++) {
                    if(nums[i]==val)
                        count++;    
                }
                if(count>N)
                    results.add(val);
            }
        }
        return results;
    }
}