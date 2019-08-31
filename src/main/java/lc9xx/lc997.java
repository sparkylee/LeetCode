package lc9xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc997 {
    public int findJudge(int N, int[][] trust) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = 0;
        for (int i = 0; i < trust.length; i++) {
            int truster = trust[i][0] - 1;
            int trustee = trust[i][1] - 1;
            arr[truster] = -1; // exclude
            if (arr[trustee] != -1)
                arr[trustee]++;
        }
        for (int i = 0; i < N; i++)
            if (arr[i] == N - 1)
                return i + 1;
        return -1;
    }
}
