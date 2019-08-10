import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lc1137 {

    public int tribonacci(int n) {
        int[] tb = {0, 1, 1};
        if (n <= 2) return tb[n];
        for (int i = 0; i <= n - 3; i++) {
            int ti = tb[0] + tb[1] + tb[2];
            tb[0] = tb[1];
            tb[1] = tb[2];
            tb[2] = ti;
        }
        return tb[2];
    }
}
