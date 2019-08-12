import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lc989 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> B = new ArrayList<>();
        do {
            int m = K % 10;
            K = K / 10;
            B.add(m);
        } while (K > 0);
        int i = 0;
        int a = 0, b = 0, c = 0, sum = 0, s = 0;
        while (true) {
            int j = A.length - 1 - i;
            a = (j >= 0) ? A[j] : 0;
            b = (i < B.size()) ? B.get(i) : 0;
            if (sum == 0) break;
            sum = a + b + c;
            s = sum % 10;
            c = sum / 10;
            if (i == B.size()) {
                B.add(s);
            }
            else
                B.set(i, s);
            i++;
        }
        Collections.reverse(B);
        return B;

    }
}
