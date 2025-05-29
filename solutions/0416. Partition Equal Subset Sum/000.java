class Solution {
    public boolean canPartition(int[] nums) {
        int sum =  Arrays.stream(nums).sum();
        if(sum%2!=0)
            return false;
        int half = sum /2;
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(n==half)
                return true;
            Set<Integer> set_new = new HashSet<>();   
            for(int v: set) {
                int v_new = v + n;
                if(v_new == half)
                    return true;
                set_new.add(v_new);
            }
            set_new.add(n);
            set.addAll(set_new);
        }
        return false;
    }
}