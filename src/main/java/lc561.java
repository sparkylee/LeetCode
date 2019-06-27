import org.junit.Test;

public class lc561 {
    @Test
    public void test() {
        t(new int[]{1, 1});
        t(new int[]{2, 1});
        t(new int[]{1, 2});
        t(new int[]{1, 4, 3, 2});
        t(new int[]{1, 4, 3, 2, 7, 8, 10, 9});
    }

    private void t(int[] nums) {
        System.out.println(arrayPairSum(nums));
        for (int n : nums)
            System.out.print(n + " ");
        System.out.println();
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end || start < 0 || end >= nums.length) return;
        int k = start, i = start, j = end;
        while (i < j) {
            if (nums[i + 1] < nums[i]) {
                swap(nums, k, i + 1);
                k++;
                i++;
                continue;
            }
            if (nums[i + 1] == nums[i]) {
                i++;
                continue;
            }
            if (i + 1 >= j)
                break;
            swap(nums, i + 1, j);
            j--;
        }
        quickSort(nums, start, k - 1);
        quickSort(nums, i + 1, end);
    }

    public int arrayPairSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2)
            sum += nums[i];
        return sum;
    }
}
