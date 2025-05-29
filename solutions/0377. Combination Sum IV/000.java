class Solution {
    int combinationSum4(int[] nums, int target, Map<Integer,Integer> map) {
        int count = 0;
        for(int i=0;i<nums.length;i++){
            int res = target - nums[i];
            if(res<0)
                break;
            if(res==0) {
                count += 1;
                break;
            }
            if(!map.containsKey(res)) {
                combinationSum4(nums,res,map);
            }
            count += map.get(res);                
        }
        map.put(target, count);
        return count;
    }
    public int combinationSum4(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        Arrays.sort(nums);
        return combinationSum4(nums,target,map);
    }
}