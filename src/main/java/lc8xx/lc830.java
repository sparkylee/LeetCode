package lc8xx;

import java.util.ArrayList;
import java.util.List;

public class lc830 {
    public List<List<Integer>> largeGroupPositions(String S) {
        int count = 0;
        int j = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= S.length(); i++) {

            int ci = i < S.length() ? S.charAt(i) : -2;
            int cp = i - 1 >= 0 ? S.charAt(i - 1) : -1;
            if (ci == cp)
                count++;
            else {
                if (count >= 3) {
                    List<Integer> l = new ArrayList<>();
                    l.add(j);
                    l.add(i - 1);
                    list.add(l);
                }
                count = 1;
                j = i;
            }
        }
        return list;
    }
}
