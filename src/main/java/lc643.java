import org.junit.Test;

public class lc643 {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k) return 0;
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        int sum_max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            sum_max = Math.max(sum, sum_max);
        }
        return (double) sum_max / (double) k;
    }
}
