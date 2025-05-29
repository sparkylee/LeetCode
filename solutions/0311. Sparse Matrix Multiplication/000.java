class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int n = mat2[0].length;
        int [][] mat3 = new int[m][n];
        for(int i=0;i<m;i++) {
              for(int j=0;j<n;j++) {
                int k = mat1[i].length;
                int value = 0;
                for(int h=0;h<k;h++) {
                    value += mat1[i][h] * mat2[h][j];
                }
                mat3[i][j] = value;
              }
        }
        return mat3;
    }
}