package lc7xx;

import java.util.ArrayList;
import java.util.List;

public class lc728 {

    private boolean isselfDividingNumber(int x) {
        int xx = x;
        while (x > 0) {
            int y = x % 10;
            if (y == 0 || xx % y != 0) return false;
            x = x / 10;
        }
        return true;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> arr = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isselfDividingNumber(i))
                arr.add(i);
        }
        return arr;
    }

}
