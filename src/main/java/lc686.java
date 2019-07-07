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
        for (int i = 0; i < shift.length; i++)
            shift[i] = -1;
        for (int i = 1; i < s.length(); i++) {
            int j = 0;
            while ((j + i) < s.length()) {
                if (s.charAt(i + j) != s.charAt(j)) break;
                if (shift[i + j] == -1) shift[i + j] = i;
                j++;
            }
        }
        return shift;
    }

    private int matchSubstring(String s, String t) {
        int[] shift = shiftIndex(s);
        int i = 0; // the Starting index of the target string
        int j = 0; // the index of the source string
        boolean isMatched = true;
        while (i + s.length() <= t.length()) {
            isMatched = true;
            for (; j < s.length(); j++) {
                //  i+j is the index of the target string
                if (s.charAt(j) != t.charAt(i + j)) {
                    if (j <= 1) { // only match zero or one character
                        i = i + 1;
                        j = 0;
                    } else {
                        int delta = shift[j - 1] == -1 ? j : shift[j - 1];
                        i = i + delta;
                        j = j - delta; // = (i+j) - (i+delta) = j - delta;
                    }
                    isMatched = false;
                    break;
                }
            }
            if (isMatched) return i;
        }
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {

        return 0;
    }
}
