import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc680 {

    @Test
    public void test() {
        t2("deeee");
        /*t2("");
        t2("aba");
        t2("abca");
        t2("abcx");
        t2("abbx");
        t2("abax");
        t2("abcbax");*/
    }

    @Test
    public void test1() {
        t1("abcba");
    }

    private void t1(String s) {
        System.out.println(validPalindromeDepth(s, 0, s.length() - 1));
    }

    private void t2(String s) {
        System.out.println(validPalindrome(s));
    }

    private int validPalindromeDepth(String s, int x, int y) {
        int count = 0;
        while (x < y && x >= 0 && x < s.length() && y < s.length()) {
            if (s.charAt(x) == s.charAt(y)) {
                x++;
                y--;
                count++;
            } else
                return count;
        }
        return count;
    }

    public boolean validPalindrome(String s) {
        if (s == null) return false;
        if (s.length() <= 2) return true;
        int count = validPalindromeDepth(s, 0, s.length() - 1);
        if (count == s.length() / 2)
            return true;
        int len = s.length() - count * 2;
        if (len <= 2) return true;
        int count1 = validPalindromeDepth(s, count, s.length() - 1 - count - 1);
        if (count1 == (len - 1) / 2)
            return true;
        int count2 = validPalindromeDepth(s, count + 1, s.length() - 1 - count);
        if (count2 == (len - 1) / 2)
            return true;
        return false;
    }
}
