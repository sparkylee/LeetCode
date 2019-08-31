package lc6xx;

import org.junit.Test;

public class lc696 {

    @Test
    public void test() {
        t("00110011");
        t("");
        t("01");
        t("0");
        t("1");
        t("11");
        t("10101");
    }

    private void t(String s) {
        System.out.println(countBinarySubstrings(s));
    }

    public int countBinarySubstrings(String s) {
        int k = 0;
        int count = 0;
        while (k < s.length()) {
            while (k + 1 < s.length() && s.charAt(k) == s.charAt(k + 1))
                k++;
            int i = 0;
            while ((k - i) >= 0 && (k + 1 + i) < s.length()
                    && s.charAt(k - i) == s.charAt(k)
                    && s.charAt(k + 1 + i) == s.charAt(k + 1)) {
                i++;
                count++;
            }
            k++;
        }
        return count;
    }
}
