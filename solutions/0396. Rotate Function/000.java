class Solution {
    public int maxRotateFunction(int[] nums) {
        int f_k_0 = 0;
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            f_k_0+= nums[i]*i;
            sum += nums[i];
        }        
        int f_k = f_k_0;
        int f_k_max = f_k;
        // System.out.println("f_k_0="+f_k_0);
        int n= nums.length;
        for(int k=1;k<nums.length;k++) {
            f_k += sum;
            f_k -= (n *  nums[n-k]);
            f_k_max = Math.max(f_k, f_k_max);
            // System.out.println("f_k="+f_k);
        }
        return f_k_max;
    }
}