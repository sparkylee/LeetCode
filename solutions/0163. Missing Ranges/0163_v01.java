class Solution {
    List<Integer> createRange(int lower, int upper) {
        List<Integer> range = new ArrayList<>();
        range.add(lower);
        range.add(upper);
        return range;
    }
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int i = 0;
        List<List<Integer>> ranges = new ArrayList<>();
        if(nums.length == 0) {
            ranges.add(createRange(lower,upper));
            return ranges;
        }
        if(lower < nums[i] ) {
            ranges.add(createRange(lower,nums[i]-1));
        }

        while(i < nums.length-1) {
            if(nums[i] + 1 == nums[i+1]) {
                i++;
                continue;
            }
            ranges.add(createRange(nums[i] + 1,nums[i+1]-1));
            i++;
        }
        if(nums[i]<upper) {
            ranges.add(createRange(nums[i]+1,upper));
        }
        return ranges;
    }
}