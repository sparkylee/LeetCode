import org.junit.Test;

public class lc334 {


    public boolean increasingTriplet(int[] nums) {
        int i = 0;
        int ii = 0;
        int j = 0;
        int k = 0;
        int jj = 0;

        j = i + 1;
        while (j < nums.length) {
            if (nums[j] > nums[i])
                break;
            j++;
        }
        k = j + 1;
        while (k < nums.length) {
            if (nums[k] > nums[j]) {
                return true;
            }
            if (nums[k] < nums[j] && nums[k] > nums[i]) {
                j = k;
                k = j + 1;
                continue;
            }
            if (nums[k] < nums[i]) {
                //look for another value l that is smaller than nums[j]
                ii = k;
                jj = ii + 1;
                while (jj < nums.length && nums[jj] <= nums[ii]) {
                    jj++;
                }
                if (jj >= nums.length)
                    return false;
                if (nums[jj] > nums[j])
                    return true;
                i = k;
                j = jj;
                k = j + 1;
            }
        }
        return false;
    }

}
