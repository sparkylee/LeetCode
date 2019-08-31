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
            map.put(x, +1);
        }
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> elem : map.entrySet()) {
            maxCount = Math.max(elem.getValue(), maxCount);
        }
        Integer[] array = new Integer[maxCount];
        for (int i = 0; i < array.length; i++)
            array[i] = null;
        for (Map.Entry<Integer, Integer> elem : map.entrySet()) {
            array[elem.getValue()] = elem.getKey();
        }
        List<Integer> results = new ArrayList<>();
        for (int i = array.length - 1; i >= 0; i--) {
            if (k == 0) break;
            if (array[i] != null) {
                results.add(array[i]);
                k--;
            }
        }
        return results;
    }

}
