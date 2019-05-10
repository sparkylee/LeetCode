import org.junit.Test;

public class isDiagonalMatrix {
    @Test
    public void test1()
    {
        int [][] matrix = {{1, 1, 0},
                {0, 5, 0},
                {0, 0, 3}};
        test(matrix);
    }

    private  void test( int [][] matrix)
    {
        boolean result  = isDiagonalMatrix(matrix);
        System.out.println(result);
    }
    boolean isDiagonalMatrix(int[][] matrix) {
        if(matrix==null || matrix.length<1) return false;
        int len = matrix.length;
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i].length!=len) return false;
            for(int j=0;j<matrix[i].length;j++)
            {
                if(i!=j && matrix[i][j]!=0) return false;
            }
        }
        return true;
    }
}
