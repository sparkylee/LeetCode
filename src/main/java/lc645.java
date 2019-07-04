public class lc645 {
    class BitSet {
        long[] ls;
        int i;

        BitSet(int n) {
            this.i = 0;
            ls = new long[n / 64 + 1];
            for (int i = 0; i < ls.length; i++)
                ls[i] = 0;
        }

        boolean get(int i) {
            return ((ls[i / 64] >> (i % 64)) & 0x1) == 1;
        }

        void set(int i, boolean v) {
            long mask = (1 << (i % 64));
            if (!v) {
                ls[i / 64] = ls[i / 64] & ~mask;
            } else
                ls[i / 64] = ls[i / 64] | mask;
        }

    }

    public int[] findErrorNums(int[] nums) {
        BitSet bs = new BitSet(nums.length);
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (bs.get(nums[i])) {
                result[0] = nums[i];
            }
            bs.set(nums[i], true);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!bs.get(nums[i])) {
                result[1] = nums[i];
                return result;
            }
        }
        return result;
    }
}
