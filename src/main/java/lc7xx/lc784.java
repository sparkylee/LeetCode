package lc7xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc784 {
    @Test
    public void test() {
        List<String> result = letterCasePermutation("C");
        for (String s : result) {
            System.out.println(s);
        }
    }

    private char toggle(char x) {
        if (x >= 'A' && x <= 'Z') return (char) (x - 'A' + 'a');
        if (x >= 'a' && x <= 'z') return (char) (x - 'a' + 'A');
        return 0;
    }

    private String permute(String s, int i) {
        char x = s.charAt(i);
        x = toggle(x);

        String snew = s.substring(0, i) + x;
        if (i + 1 < s.length())
            snew += s.substring(i + 1);
        return snew;

    }

    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        if (S == null || S.length() == 0)
            return list;
        list.add(S);
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c >= '0' && c <= '9')
                continue;
            int len = list.size();
            for (int j = 0; j < len; j++) {
                String x = permute(list.get(j), i);
                list.add(x);
            }
        }
        return list;
    }

}
