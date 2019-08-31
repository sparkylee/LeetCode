package lc7xx;

import org.junit.Test;

import java.util.BitSet;

public class lc746 {
    public int minCostClimbingStairs(int[] cost) {
        int[] total = new int[cost.length];
        total[0] = cost[0];
        total[1] = cost[1];
        final int len = total.length;
        for (int i = 2; i < len; i++) {
            total[i] = Math.min(total[i - 2], total[i - 1]) + cost[i];
        }

        return Math.min(total[len - 1], total[len - 2]);
    }
}
