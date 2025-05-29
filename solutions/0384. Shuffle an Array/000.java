import java.security.SecureRandom;
class Solution {
    SecureRandom rand;
    int [] nums;
    int [] nums_cp;
    int [] nums_rt;
    int N ;

    public Solution(int[] nums) {
        this.rand = new SecureRandom();
        this.nums = nums;
        this.N = nums.length;
        nums_cp = Arrays.copyOf(nums, N);
        nums_rt = Arrays.copyOf(nums, N);
        
    }
    
    public int[] reset() {
          return nums;
    }
    
    public int[] shuffle() {
        int r = Math.abs(rand.nextInt());
        for (int i=0; i < N; i++)
        {
            int d = N - i;
            int j = r % d;
            nums_rt[i] = nums_cp[j];
            int tmp    = nums_cp[j];
            nums_cp[j] = nums_cp[d-1];
            nums_cp[d-1] = tmp;
        }
        return nums_rt;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */