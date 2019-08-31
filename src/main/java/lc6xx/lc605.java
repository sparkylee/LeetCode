package lc6xx;

import org.junit.Test;

public class lc605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int i = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 1 || (i - 1 >= 0 && flowerbed[i - 1] == 1)
                    || (i + 1 < flowerbed.length && flowerbed[i + 1] == 1)) {
                i++;
                continue;
            }
            flowerbed[i] = 1;
            count++;
            i += 2;
        }
        return count >= n;
    }
}
