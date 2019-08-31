package lc10xx;

public class lc1021 {
    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        boolean isClosed = true;
        int lp = 0, rp = 0;
        for (int i = 0; i < S.length(); i++) {
            if (isClosed) {
                isClosed = false;
                lp = 0;
                rp = 0;
                continue;
            }
            char c = S.charAt(i);
            if (c == '(') lp++;
            if (c == ')') rp++;
            if (rp == lp + 1) {
                isClosed = true;
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
