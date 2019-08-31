package lc6xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc686 {
    @Test
    public void test() {
//        int count = repeatedStringMatch("abcd", "cdabcdab");
        int count = repeatedStringMatch("a", "aa");
        System.out.println(count);
    }

    @Test
    public void t1() {
        int[] result = shiftIndex("abcax");
        int i = matchSubstring("axa", "abaxcaxadt");
        i = matchSubstring("aa", "aa");
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
                if (s.charAt(j) == t.charAt(i + j)) continue;
                if (j <= 1) { // only match zero or one character
                    i = i + 1;
                    j = 0;
                } else { // matched with two or more characters
                    // shift[0] is always -1 thus j must be greater than 1
                    int delta = shift[j - 1] == -1 ? j : shift[j - 1];
                    // the starting index of the target string is pushed forward
                    i = i + delta;
                    // the starting index of the source string is pushed backward
                    j = j - delta; // = (i+j) - (i+delta) = j - delta;
                }
                isMatched = false;
                break;
            }
            if (isMatched) return i;
        }
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder("");
        int k = 0;
        do {
            sb.append(A);
            k++;
            if (sb.length() < B.length()) continue;
            if (matchSubstring(B, sb.toString()) >= 0)
                return k;
        } while (sb.length() <= B.length() + 2 * (A.length() - 1));
        return -1;
    }
}
