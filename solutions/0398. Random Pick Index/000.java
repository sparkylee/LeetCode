class Solution {
    int [] nums;
    Map<Integer, List<Integer>> map;
    Random rand ;
    public Solution(int[] nums) {
        this.nums = nums;
        this.map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            List<Integer> lst = this.map.getOrDefault(val, new ArrayList<>());
            lst.add(i);
            this.map.putIfAbsent(val, lst);
        }
        this.rand = new Random();
    }
    
    public int pick(int target) {
        if(!this.map.containsKey(target))
            return -1;
        List<Integer> lst = this.map.get(target);
        return lst.get(this.rand.nextInt(lst.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */