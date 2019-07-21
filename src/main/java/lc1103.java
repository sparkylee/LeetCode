import org.junit.Test;

import java.util.Arrays;

public class lc1103 {
    @Test
    public void test() {
        int[] results = distributeCandies(10, 3);
        System.out.println(Arrays.toString(results));
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] results = new int[num_people];
        int n = num_people;
        int k = -1;
        int base_sum = 0;
        int ns = (n + 1) * n / 2;
        int n2 = n * n;
        while (true) {
            int delta = ns + (k + 1) * n2;
            if (delta + base_sum <= candies) {
                base_sum += delta;
                k++;
            } else
                break;
        }
        if (k >= 0) {
            int base = k * (k + 1) * n / 2;
            for (int i = 1; i <= n; i++)
                results[i - 1] = i * (k + 1) + base;
        }
        candies = candies - base_sum;
        int bottom = (k + 1) * n;
        int i = 1;
        while (true) {
            int assign = i + bottom;
            if (candies >= assign) {
                results[i - 1] += assign;
                candies -= assign;
            }
            else {
                results[i - 1] += candies;
                break;
            }
            i++;
        }
        return results;
    }
}
