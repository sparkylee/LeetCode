import java.util.*;

public class lc754 {
    public int reachNumber(int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int k = 1;
        while (true) {
            if (map.containsKey(target)) return map.get(target);
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> pair = (Map.Entry) it.next();
                int tl = pair.getKey() - k;
                int tr = pair.getKey() + k;
                map.putIfAbsent(tl, k);
                map.putIfAbsent(tr, k);
            }
            k++;
        }
    }
}
