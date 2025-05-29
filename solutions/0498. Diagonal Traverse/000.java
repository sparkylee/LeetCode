class Solution {
    int m, n;
    boolean isInRange(int i, int j) {
        return i>=0 && i < this.m && j >=0 && j < this.n;
    }
    public int[] findDiagonalOrder(int[][] mat) {
        int i = 0, j = 0;
        this.m = mat.length;
        this.n = mat[0].length;
        int count = this.m*this.n;
        int [] arr = new int[count];
        int k = 0;
        int di = -1, dj = 1;
        while(true) {
            arr[k] = mat[i][j];
            i = i + di;
            j = j + dj ;
            k ++;
            if(k==count)
                break;
            if(isInRange(i,j))
                continue;
            di = -di;
            dj = -dj;
            i++;
            while(!isInRange(i,j)){
                i += di;
                j += dj;
            }
        }
        return arr;
    }
}