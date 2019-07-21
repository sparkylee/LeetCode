import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lc1122 {

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
            map.computeIfPresent(arr1[k], (x, v) -> v++);
        int y = 0;
        for (int k = 0; k < arr2.length; k++) {
            int count = map.get(arr2[k]);
            for (int l = 0; l < count; l++) {
                arr1[y] = arr2[k];
                y++;
            }
        }
        return arr1;

    }
}
