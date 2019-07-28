import java.util.*;

public class lc888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int diff = Arrays.stream(A).sum() - Arrays.stream(B).sum();
        for (int a : A) {
            int b = a - diff;
            int index = Arrays.binarySearch(B, b);
            if (index >= 0) {
                return new int[]{a, b};
            }
        }

        return null;
    }
}
