package lc11xx;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lc1122 {

    @Test
    public void test() {
        int[] results = relativeSortArray(
                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19},
                new int[]{2, 1, 4, 3, 9, 6}
        );
        System.out.println(Arrays.toString(results));
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr2) map.put(x, 0);
        int i = 0, j = arr1.length - 1;
        while (true) {
            while (i < arr1.length && map.containsKey(arr1[i]))
                i++;
            while (j >= 0 && !map.containsKey(arr1[j]))
                j--;
            if (i < j)
                swap(arr1, i, j);
            else
                break;
        }
        for (int k = 0; k < i; k++)
            map.computeIfPresent(arr1[k], (x, v) -> v = v + 1);
        int y = 0;
        for (int k = 0; k < arr2.length; k++) {
            int count = map.get(arr2[k]);
            for (int l = 0; l < count; l++) {
                arr1[y] = arr2[k];
                y++;
            }
        }
        Arrays.sort(arr1, i, arr1.length);
        return arr1;

    }
}
