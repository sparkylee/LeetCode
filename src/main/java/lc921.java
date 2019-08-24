public class lc921 {
    public int minAddToMakeValid(String S) {
        int count = 0;
        int p = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                if (p > 0) p = 0;
                p--;
            } else {
                p++;
                if (p > 0) count++;
            }
        }
        if (p < 0) count += -p;
        return count;
    }
}
