package lc5xx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc599 {
    Map<String, Integer> createMapping(String[] list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }
        return map;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map2 = createMapping(list2);
        List<String> list = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < list1.length; i++) {
            Integer j = map2.get(list1[i]);
            if (j == null || ((i + j) > count && count != -1)) continue;
            if ((i + j) == count) {
                list.add(list1[i]);
            }
            if ((i + j) < count || count == -1) {
                list.clear();
                list.add(list1[i]);
                count = i + j;
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
