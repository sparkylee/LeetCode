import org.junit.Test;

public class lc334 {


    public boolean increasingTriplet(int[] nums) {
        int i = 0, j = 0, k = 0, jj = 0;
        j = i + 1;
        if (i >= nums.length)
            return false;
        while (j < nums.length && nums[j] <= nums[i])
            j++;
        if (j >= nums.length)
            return false;
        k = j + 1;
        while (k < nums.length) {
            if (nums[k] > nums[j])
                return true;
            if (nums[k] <= nums[j] && nums[k] >= nums[i]) {
                j = k;
                k = j + 1;
                continue;
            }
            if (nums[k] < nums[i]) {
                //look for another value that is smaller than nums[j]
                jj = k + 1;
                while (jj < nums.length && nums[jj] <= nums[k]) {
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
