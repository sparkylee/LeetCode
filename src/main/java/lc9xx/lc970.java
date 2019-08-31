package lc9xx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc970 {
    List<Integer> getList(int a, int bound) {
        List<Integer> l = new ArrayList<>();
        int ap = 1;
        while (ap <= bound) {
            l.add(ap);
            if (a == 1) return l;
            ap = ap * a;
        }
        return l;
    }

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> lx = getList(x, bound);
        List<Integer> ly = getList(y, bound);
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < lx.size(); i++) {
            int x2 = lx.get(i);
            for (int j = 0; j < ly.size(); j++) {
                int sum = x2 + ly.get(j);
                if (sum <= bound)
                    s.add(sum);
                else
                    break;
            }
        }
        List<Integer> mainList = new ArrayList<>();
        mainList.addAll(s);
        return mainList;
    }
}
