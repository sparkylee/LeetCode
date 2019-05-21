import org.junit.Test;

public class lc334 {
    int i = 0;
    private int ii = 0;
    int j = 0;
    int k = 0;
    private int jj = 0;

    public boolean increasingTriplet(int[] nums) {
        this.j = this.i + 1;
        while (this.j < nums.length) {
            if (nums[this.j] > nums[this.i])
                break;
            this.j++;
        }
        this.k = this.j + 1;
        while (this.k < nums.length) {
            if (nums[this.k] > nums[this.j]) {
                return true;
            }
            if (nums[this.k] < nums[this.j] && nums[this.k] > nums[this.i]) {
                this.j = this.k;
                this.k = this.j + 1;
                continue;
            }
            if (nums[this.k] < nums[this.i]) {
                //look for another value l that is smaller than nums[this.j]
                this.ii = k;
                this.jj = this.ii + 1;
                while (this.jj < nums.length && nums[this.jj] <= nums[this.ii]) {
                    this.jj++;
                }
                if (this.jj >= nums.length)
                    return false;
                if (nums[this.jj] > nums[this.j])
                    return true;
                this.i = this.k;
                this.j = this.jj;
                this.k = this.j + 1;
            }
        }
        return false;
    }

}
