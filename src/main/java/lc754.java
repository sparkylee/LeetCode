import java.util.*;

public class lc754 {
    public int reachNumber(int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int k = 1;
        while (true) {
            if (map.containsKey(target)) return map.get(target);
            Map<Integer, Integer> mapNew = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int tl = entry.getKey() - k;
                int tr = entry.getKey() + k;
                mapNew.putIfAbsent(tl, k);
                mapNew.putIfAbsent(tr, k);
            }
            Iterator it = mapNew.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> pair = (Map.Entry) it.next();
                map.putIfAbsent(pair.getKey(), pair.getValue());
            }
            k++;
        }
    }
}
