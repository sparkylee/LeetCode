public class lc985 {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] answers = new int[queries.length];
        int evenSum = 0;
        for (int i = 0; i < A.length; i++)
            if (A[i] % 2 == 0)
                evenSum += A[i];
        for (int i = 0; i < answers.length; i++) {
            int index = queries[i][1];
            int val_N = queries[i][0];
            int val_A = A[index];
            if (val_A % 2 == 0 && val_N % 2 == 1)
                evenSum = evenSum - val_A;
            if (val_A % 2 == 1 && val_N % 2 == 1)
                evenSum = evenSum + val_A + val_N;
            if (val_A % 2 == 0 && val_N % 2 == 0)
                evenSum = evenSum + val_A;
            answers[i] = evenSum;
            A[index] = val_A + val_N;
        }
        return answers;
    }
}
