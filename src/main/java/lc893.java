import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class lc893 {
    @Test
    public void test() {

    }

    int[][] oe = new int[2][26];

    private void reset() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 26; i++) {
                oe[j][i] = 0;
            }
        }
    }

    StringBuilder sb = new StringBuilder();
    private String calcSESignature(String str) {
        sb.setLength(0);
        sb.trimToSize();
        reset();
        for (int i = 0; i < str.length(); i += 2) {
            char c = str.charAt(i);
            oe[0][c - 'a']++;
        }
        for (int i = 1; i < str.length(); i += 2) {
            char c = str.charAt(i);
            oe[1][c - 'a']++;
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 26; i++) {
                if (oe[j][i] == 0) continue;
                sb.append('a' + i);
                if (oe[j][i] > 1)
                    sb.append(oe[j][i] + 'A');
            }
            sb.append('&');
        }
        return sb.toString();
    }

    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            String str = calcSESignature(A[i]);
            set.add(str);
        }
        return set.size();
    }
}
