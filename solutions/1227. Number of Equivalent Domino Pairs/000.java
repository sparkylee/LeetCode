class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
           int[][] dcnt = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                dcnt[i][j] = 0;
        for (int i = 0; i < dominoes.length; i++) {
            if (dominoes[i][0] > dominoes[i][1]) {
                int tmp = dominoes[i][0];
                dominoes[i][0] = dominoes[i][1];
                ;
                dominoes[i][1] = tmp;
            }
            dcnt[dominoes[i][0] - 1][dominoes[i][1] - 1]++;
        }
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                int n = dcnt[i][j];
                count += n * (n - 1) / 2;
            }
        return count;
    }
}