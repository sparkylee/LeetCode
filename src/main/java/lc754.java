import org.junit.Test;

import java.util.*;

public class lc754 {
    @Test
    public void test() {
        int result = reachNumber(2);
    }

    public int reachNumber(int target) {

        Set<Integer> pre = new HashSet<>();
        int k = 0;
        if (target == 0) return 0;
        while (true) {
            k++;
            Set<Integer> setNew = new HashSet<>();
            for (Integer x : pre) {
                int tl = x - k;
                int tr = x + k;
                if (tl == target || tr == target)
                    return k;
                setNew.add(tl);
                setNew.add(tr);
            }
            pre = setNew;
        }
    }
}
