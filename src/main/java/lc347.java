import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            int count = 1;
            if (map.containsKey(x))
                count += map.get(x);
            map.put(x, count);
        }
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> elem : map.entrySet()) {
            maxCount = Math.max(elem.getValue(), maxCount);
        }
        List<List<Integer>> array = new ArrayList<>();
        for (int i = 0; i < maxCount; i++)
            array.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> elem : map.entrySet())
            array.get(elem.getValue() - 1).add(elem.getKey());

        List<Integer> results = new ArrayList<>();
        for (int i = array.size() - 1; i >= 0; i--) {
            if (k == 0) break;
            List<Integer> lst = array.get(i);
            if (!lst.isEmpty()) {
                results.addAll(lst);
                k = k - lst.size();
            }
        }
        return results;
    }

}
