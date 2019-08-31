package lc10xx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc1013 {
    private int nextThird(int[] A, int start, int tripple) {
        int i = start;
        int s = 0;
        while (i < A.length) {
            s += A[i];
            if (s == tripple) return i;
            i++;
        }
        return -1;
    }

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = Arrays.stream(A).sum();
        if (sum % 3 != 0) return false;
        int tripple = sum / 3;
        int i = nextThird(A, 0, tripple);
        if (i == -1) return false;
        i = nextThird(A, i + 1, tripple);
        return i >= 0 && i < A.length - 1;
    }
}
