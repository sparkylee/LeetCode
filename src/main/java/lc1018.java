import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc1018 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int m = 0;
        for (int i = 0; i < A.length; i++) {
            m = (2 * m + A[i]) % 5;
            list.add(m == 0);
        }
        return list;
    }
}
