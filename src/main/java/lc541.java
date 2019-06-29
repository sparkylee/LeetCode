import org.junit.Test;

public class lc541 {
    @Test
    public void test() {
        t("abcdefg", 2);
    }

    public void t(String s, int k) {
        System.out.println((reverseStr(s, k)));
    }

    void swap(StringBuilder sb, int i, int j) {
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }

    void reverseSB(StringBuilder sb, int start, int end) {
        if (start < 0 || start >= end || end >= sb.length()) return;
        for (int i = 0; i <= (start + end) / 2; i++) {
            if (start + i >= end - i) break;
            swap(sb, start + i, end - i);

        }
    }

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int x = 0;
        int start = 0;
        while ((start = x * k * 2) < sb.length()) {
            int end = start + k - 1;
            end = Math.min(end, sb.length() - 1);
            reverseSB(sb, start, end);
            x++;
        }
        return sb.toString();
    }

}
