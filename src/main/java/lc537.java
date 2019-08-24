import org.junit.Test;

public class lc537 {
    @Test
    public void test() {
//        s2c("1+1i");
        s2c("1+-1i");
    }

    private int[] s2c(String s) {
        String[] strs = s.split("\\+");
        int x = Integer.parseInt(strs[0]);
        int y = Integer.parseInt(strs[1].substring(0, strs[1].length() - 1));
        return new int[]{x, y};
    }

    private int[] cc(int[] c0, int[] c1) {
        int x = c0[0] * c1[0] - c0[1] * c1[1];
        int y = c0[0] * c1[1] + c0[1] * c1[0];
        return new int[]{x, y};
    }

    private String c2s(int[] complex) {
        return String.valueOf(complex[0]) + ":" + String.valueOf(complex[1]) + "i";
    }

    public String complexNumberMultiply(String a, String b) {
        int[] ac = s2c(a);
        int[] bc = s2c(b);
        int[] c = cc(ac, bc);
        return c2s(c);
    }
}
