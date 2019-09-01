package lc4xx;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lc454 {
    @Test
    public void test() {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        fourSumCount(A, B, C, D);
    }

    @Test
    public void test1() {
        int[] A = {1, 2, 2};
        int[] B = {-2, -1, -1};
        int[] C = {-1, 2, -1};
        int[] D = {0, 2, 0};
        fourSumCount(A, B, C, D);
    }
    class SArray {
        int[] X;
        int[] Y;

        SArray(int[] X, int[] Y) {
            this.X = X;
            this.Y = Y;
        }
    }

    private int countUnique(int[] X) {
        if (X.length <= 1) return X.length;
        int count = 1;
        for (int k = 0; k < X.length - 1; k++)
            if (X[k] != X[k + 1]) count++;
        return count;
    }

    private SArray convertSortedArray(int[] Z) {
        int count = countUnique(Z);
        int[] X = new int[count];
        int[] Y = new int[count];
        int i = 0;
        int k = 0;
        while (k < Z.length) {
            X[i] = Z[k];
            int j = k;
            while (j < Z.length && Z[j] == Z[k]) {
                Y[i]++;
                j++;
            }
            i++;
            k = j;
        }
        return new SArray(X, Y);
    }

    private int findValue(int[] X, int start, int end, int v) {
        if (start < 0 || end >= X.length || start > end) return -1;
        int middle = (start + end) / 2;
        if (X[middle] == v) return middle;
        if (X[middle] < v) return findValue(X, middle + 1, end, v);
        return findValue(X, start, middle - 1, v);
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        SArray AS = convertSortedArray(A);
        Arrays.sort(B);
        SArray BS = convertSortedArray(B);
        Arrays.sort(C);
        SArray CS = convertSortedArray(C);
        Arrays.sort(D);
        SArray DS = convertSortedArray(D);
        Map<Integer, Integer> Dmap = new HashMap<>();
        for (int a = 0; a < DS.X.length; a++)
            Dmap.put(DS.X[a], DS.Y[a]);
        int count = 0;
        for (int i = 0; i < AS.X.length; i++) {
            for (int j = 0; j < BS.X.length; j++) {
                for (int k = 0; k < CS.X.length; k++) {
                    int v = -(AS.X[i] + BS.X[j] + CS.X[k]);
                    if (DS.X.length > 0 && DS.X[0] > v)
                        break;
//                    int l = findValue(DS.X, 0, DS.X.length - 1, v);
                    Integer lc = Dmap.get(v);
                    if (lc != null)
                        count += AS.Y[i] * BS.Y[j] * CS.Y[k] * lc;
                }
            }
        }
        return count;
    }
}
