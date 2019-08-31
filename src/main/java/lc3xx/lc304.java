package lc3xx;

import org.junit.Test;

public  class lc304
{

    @Test
    public void test1() {

        int [][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        tc(matrix,new int[] {2,1,4,3});
        tc(matrix,new int[] {1,1,2,2});
        tc(matrix,new int[] {1,2,2,4});
    }

    private void tc(int[][] matrix, int[] coordinates) {
        NumMatrix s = new NumMatrix(matrix);
        int result = s.sumRegion(coordinates[0],coordinates[1],coordinates[2],coordinates[3]);
        System.out.println(result);
    }

    class NumMatrix {
        int[][] matrix_TL;
        int[][] matrix_TR;
        int[][] matrix_BR;
        int[][] matrix_BL;
        int[][] matrix;
        int height ;
        int width  ;
        int total;

        public NumMatrix(int[][] matrix) {
            if(matrix==null || matrix.length<1) return;
            this.matrix = matrix;
            this.height = matrix.length;
            this.width = matrix[0].length;
            this.matrix_TL = new int [height][width];
            this.matrix_TR = new int [height][width];
            this.matrix_BR = new int [height][width];
            this.matrix_BL = new int [height][width];

            for (int j = 0; j < height; j++) {
                int line = 0;
                for (int i = 0; i < width; i++) {
                    line+=matrix[j][i];
                    int grid_pre = (j-1<0)?0:matrix_TL[j-1][i];
                    this.matrix_TL[j][i] = grid_pre+ line;
                }
            }
            for (int j = height - 1; j >= 0; j--) {
                int line = 0;
                for (int i = 0; i < width; i++) {
                    line+=matrix[j][i];
                    int grid_pre = (j+1>=height)?0:matrix_BL[j+1][i];
                    this.matrix_BL[j][i] = grid_pre+ line;
                }
            }
            for (int j = 0; j < height; j++) {
                int line = 0;
                for (int i = width - 1; i >= 0; i--) {
                    line+=matrix[j][i];
                    int grid_pre = (j-1<0)?0:matrix_TR[j-1][i];
                    this.matrix_TR[j][i] = grid_pre+ line;
                }
            }
            for (int j = height - 1; j >= 0; j--) {
                int line = 0;
                for (int i = width - 1; i >= 0; i--) {
                    line+=matrix[j][i];
                    int grid_pre = (j+1>=height)?0:matrix_BR[j+1][i];
                    this.matrix_BR[j][i] = grid_pre+ line;
                }
            }
            this.total = this.matrix_TL[height-1][width-1];
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int tl_row = row2,tl_col = col1 -1;
            int tl = tl_col<0?0:this.matrix_TL[tl_row][tl_col];
            int tr_row = row1 -1, tr_col = col1;
            int tr = tr_row<0?0:this.matrix_TR[tr_row][tr_col];
            int br_row = row1, br_col = col2 + 1;
            int br = br_col>=this.width ? 0:matrix_BR[br_row][br_col];
            int bl_row = row2+1,bl_col = col2;
            int bl = bl_row >= this.height? 0: matrix_BL[bl_row][bl_col];
            return this.total - tl-tr-br-bl;
        }
    }

}
