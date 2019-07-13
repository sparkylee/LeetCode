import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class lc1046 {
    private void swap(int[] stones, int i, int j) {
        int tmp = stones[i];
        stones[i] = stones[j];
        stones[j] = tmp;
    }

    private void selectMax(int[] stones, int i) {
        if (i >= stones.length) return;
        for (int j = i + 1; j < stones.length; j++) {
            if (stones[j] > stones[i])
                swap(stones, i, j);
        }
    }

    public int lastStoneWeight(int[] stones) {
        int i = 0;
        while (true) {
            if (i == stones.length) return 0;
            if (i == stones.length - 1) return stones[i];
            selectMax(stones, i);
            selectMax(stones, i + 1);
            int abs = Math.abs(stones[i] - stones[i + 1]);
            if (abs == 0)
                i += 2;
            else {
                stones[i + 1] = abs;
                i++;
            }
        }
    }
}
