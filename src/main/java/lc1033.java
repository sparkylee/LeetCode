import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc1033 {
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        int[] result = {0, 0};
        int diff = arr[2] - arr[0];
        int d0 = arr[1] - arr[0];
        int d1 = arr[2] - arr[1];
        int d = Math.min(d0, d1);

        result[0] = diff == 2 ? 0 : (d <= 2 ? 1 : 2);
        result[1] = diff - 2;
        return result;
    }
}
