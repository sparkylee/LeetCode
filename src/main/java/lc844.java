import org.junit.Test;

public class lc844 {
    private int nextChar(String x, int i) {
        int count = 0;
        while (i >= 0) {
            char c = x.charAt(i);
            if (c != '#' && count == 0) return i;
            count += c == '#' ? 1 : -1;
            i--;
        }
        return i;
    }

    public boolean backspaceCompare(String S, String T) {
        int si = S.length() - 1;
        int ti = T.length() - 1;
        while (true) {
            si = nextChar(S, si);
            ti = nextChar(T, ti);
            if (si == -1 && ti == -1) return true;
            if (si == -1 || ti == -1) return false;
            char cs = S.charAt(si), ct = T.charAt(ti);
            if (cs != ct) return false;
            si--;
            ti--;
        }
    }
}
