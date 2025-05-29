class NumMatrix {
    int [][] matrix;
    int [][] sums;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.sums = new int[this.matrix.length][this.matrix[0].length];
        for(int i=0;i<this.matrix.length;i++) {
            int sum = 0;
            for(int j=0;j<this.matrix[i].length;j++) {
                sum+=this.matrix[i][j];
                this.sums[i][j]= sum;
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int delta = val - this.matrix[row][col];
        this.matrix[row][col] = val;
        for(int j=0;j<this.sums[row].length;j++) {
            this.sums[row][j] += (j>=col) ? delta:0;
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int i=row1;i<=row2;i++) {           
            sum += (this.sums[i][col2] - this.sums[i][col1] + this.matrix[i][col1]);
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */