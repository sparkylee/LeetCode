class Solution {
    public List<Integer> findDuplicates(int[] nums) {
       List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                int v = nums[i];
                if (i + 1 == v || nums[v - 1] == v) break;
                int tmp = nums[v - 1];
                nums[v - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i])
                results.add(nums[i]);
        }
        return results;
    }
}