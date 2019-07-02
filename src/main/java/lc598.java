import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc598 {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(op[0], m);
            n = Math.min(op[1], n);
        }
        return m * n;
    }
}
