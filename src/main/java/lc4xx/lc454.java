package lc4xx;

import java.util.Arrays;

public class lc454 {
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
        while (i < count) {
            X[i] = Z[i];
            int j = i;
            while (j < count && Z[j] == Z[i]) {
                Y[i]++;
                j++;
            }
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
        int count = 0;
        for (int i = 0; i < AS.X.length; i++) {
            for (int j = 0; i < BS.X.length; j++) {
                for (int k = 0; k < CS.X.length; k++) {
                    int v = -(AS.X[i] + BS.X[j] + CS.X[k]);
                    int l = findValue(DS.X, 0, DS.X.length - 1, v);
                    if (l >= 0)
                        count += AS.Y[i] * BS.Y[j] * CS.Y[k] * DS.Y[l];
                }
            }
        }
        return count;
    }
}
