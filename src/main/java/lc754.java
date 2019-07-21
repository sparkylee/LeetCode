import org.junit.Test;

import java.util.*;

public class lc754 {
    @Test
    public void test() {
        int result = reachNumber(2);
    }

    public int reachNumber(int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        Set<Integer> pre = new HashSet<>(set);
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
                if (!set.contains(tl)) {
                    set.add(tl);
                    setNew.add(tl);
                }
                if (!set.contains(tr)) {
                    set.add(tr);
                    setNew.add(tr);
                }
            }
            pre = setNew;
        }
    }
}
