import org.junit.Test;

import java.util.Collections;
import java.util.Random;

public class lc645 {

    public static int[] RandomizeArray(int[] array) {
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    @Test
    public void test() {
//        t(new int[] {1,2,2,4});
        int len = 230;
        int missing = 79;
        int duplicate = 119;
        int[] inputs = new int[len];
        for (int i = 0; i < len; i++) {
            inputs[i] = i + 1;
        }
        inputs[missing] = inputs[duplicate];
//        RandomizeArray(inputs);
        t(inputs);
    }

    private void t(int[] nums) {
        int[] result = findErrorNums(nums);
        System.out.println(result[0] + " " + result[1]);
    }

    class BitSet {
        // do not use long because shifting only valid for 32 bit
        int[] ls;
        int i;

        BitSet(int n) {
            this.i = 0;
            ls = new int[n / 32 + 1];
            for (int i = 0; i < ls.length; i++)
                ls[i] = 0;
        }

        boolean get(int i) {
            return ((ls[i / 32] >> (i % 32)) & 0x1) == 1;
        }

        void set(int i, boolean v) {
            int mask = (1 << (i % 32));
            if (!v) {
                ls[i / 32] = ls[i / 32] & ~mask;
            } else
                ls[i / 32] = ls[i / 32] | mask;
        }

    }

    public int[] findErrorNums(int[] nums) {
        BitSet bs = new BitSet(nums.length);
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 6)
                System.out.println("d");
            if (bs.get(nums[i] - 1)) {
                result[0] = nums[i];
            }
            bs.set(nums[i] - 1, true);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!bs.get(i)) {
                result[1] = i + 1;
                return result;
            }
        }
        return result;
    }
}
