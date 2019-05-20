import org.junit.Test;

public class lc334 {
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    boolean isFound = false;

    private void getInitPair(int[] nums) {
        this.j = this.i + 1;
        while (this.j < nums.length) {
            if (nums[this.j] > nums[this.i])
                return;
            this.j++;
        }
    }

    private void moveNextPair(int[] nums) {
        this.k = this.j + 1;
        while (this.k < nums.length) {
            if (nums[this.k] > nums[this.j]) {
                this.isFound = true;
                return;
            }
            if (nums[this.k] < nums[this.j] && nums[this.k] > nums[this.i]) {
            }
            if (nums[this.k] < nums[this.i]) {
                //look for another value l that is smaller than nums[this.j]
            }
        }
    }

    public boolean increasingTriplet(int[] nums) {
        return false;
    }

}
