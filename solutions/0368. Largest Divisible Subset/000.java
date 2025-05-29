class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int [] counts = new int[nums.length];
        int [] nexts  = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            int count = 0;
            int next = -1;
            int val_i = nums[i];
            for(int j=i-1;j>=0;j--) {
                int val_j = nums[j];
                if(val_i%val_j==0 && counts[j]>count) {
                    count =  counts[j];
                    next  = j;
                }
            }
            count++;
            counts[i] = count;
            nexts[i] = next;
        }
        int count = 0;
        int next = 0;
        for(int i=0;i<nums.length;i++) {
            if(counts[i]>count){
                count = counts[i];
                next = i;
            }
        }        
        List<Integer> results = new ArrayList<>();
        while (next!=-1) {
            results.add(nums[next]);
            next = nexts[next];
        }
        return results;
    }
}