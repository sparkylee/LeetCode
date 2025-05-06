class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int []> arr = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            List<Integer> indices = map.getOrDefault(nums[i], new ArrayList<>());
            indices.add(i);
            map.putIfAbsent(nums[i], indices);
        }
        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            List<Integer> indices;
            if(!map.containsKey(num) || (indices=map.get(num)).isEmpty())
                continue;
            int index1 = indices.getLast();
            indices.removeLast();
            int other = target - num;
            if(!map.containsKey(other) || (indices=map.get(other)).isEmpty())
                continue;
            int index2 = indices.getLast();
            indices.removeLast();
            arr.add(new int [] {index1, index2});
        }
        return arr.isEmpty()? new int[] {0,0}: arr.get(0);
    }
}