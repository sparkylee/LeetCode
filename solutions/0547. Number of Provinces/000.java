class Solution {
    private void findCircleNum(int[][] M, int i, int[] marks, int k) {
        if (i < 0 || i >= M.length || marks[i] >= 0) return;
        marks[i] = k;
        for (int j = 0; j < M[i].length; j++)
            if (M[i][j] == 1)
                findCircleNum(M, j, marks, k);
    }

    public int findCircleNum(int[][] M) {
        int[] marks = new int[M.length];
        for (int i = 0; i < marks.length; i++)
            marks[i] = -1;
        int k = 0;
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0) k++;
            findCircleNum(M, i, marks, k);
        }
        return k;
    }
}