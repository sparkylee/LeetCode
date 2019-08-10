import java.util.ArrayList;
import java.util.List;

public class lc1089 {
    public void duplicateZeros(int[] arr) {
        int shift = 0;
        int i = 0;
        for (; i < arr.length; i++) {
            if (i + shift == arr.length - 1)
                break;
            if (arr[i] == 0)
                shift++;
        }
        for (; i >= 0; i--) {
            arr[i + shift] = arr[i];
            if (arr[i] == 0) {
                shift--;
                arr[i + shift] = arr[i];
            }
        }

    }
}
