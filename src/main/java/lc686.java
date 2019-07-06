import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc686 {
    @Test
    public void t1() {
        int[] result = shiftIndex("abcax");
        int i = matchSubstring("axa", "abaxcaxadt");
        System.out.println(i);
        System.out.println("dfafsd");
    }

    private int[] shiftIndex(String s) {
        int[] shift = new int[s.length()];
        for (int j = 0; j < shift.length; j++)
            shift[j] = -1;
        for (int i = 1; i < s.length(); i++) {
            int j = 0;
            while ((j + i) < s.length()) {
                if (s.charAt(j + i) == s.charAt(j)) {
                    if (shift[i + j] == -1)
                        shift[i + j] = i;
                    j++;
                } else
                    break;
            }
        }
        return shift;
    }

    private int matchSubstring(String s, String t) {
        int[] shift = shiftIndex(s);
        int i = 0;
        boolean isMatched = true;
        while (i + s.length() <= t.length()) {
            isMatched = true;
            if (s.charAt(0) != t.charAt(i)) {
                i++;
                continue;
            }
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    i += shift[j] == -1 ? j : shift[j];
                    isMatched = false;
                    break;
                }
            }
            if (isMatched)
                return i;
        }
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {

        return 0;
    }
}
