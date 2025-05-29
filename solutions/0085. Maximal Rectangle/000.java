class Solution {
       int width,height;
        int area = 0;

        private boolean isLeftExpendable(char[][] matrix,int start_i,int start_j,int end_i,int end_j)
        {
            int start_i_new = start_i-1;
            if(start_i_new<0) return false;
            for(int j=start_j;j<=end_j;j++)
            {
                if(matrix[j][start_i_new]=='0') return false;
            }
            return true;
        }
        private boolean isRightExpendable(char[][] matrix,int start_i,int start_j,int end_i,int end_j)
        {
            if(end_i+1>=width) return false;
            for(int j=start_j;j<=end_j;j++)
            {
                if(matrix[j][end_i+1]=='0') return false;
            }
            return true;
        }
        private boolean isBottomExpendable(char[][] matrix,int start_i,int start_j,int end_i,int end_j)
        {
            if(end_j+1>=this.height) return false;
            for(int i=start_i;i<=end_i;i++)
            {
                if(matrix[end_j+1][i]=='0') return false;
            }
            return true;
        }
        private boolean isTopExpendable(char[][] matrix,int start_i,int start_j,int end_i,int end_j)
        {
            start_j = start_j - 1;
            if(start_j<0) return false;
            for(int i=start_i;i<=end_i;i++)
            {
                if(matrix[start_j][i]=='0') return false;
            }
            return true;
        }
        private void markSelectedStart(char[][] matrix,int start_i,int start_j,int end_i,int end_j)
        {
            for(int i=start_i;i<=end_i;i++)
            {
                for(int j=start_j;j<=end_j;j++)
                {
                    matrix[j][i] = '2';//these points will not be selected as the starting point.
                }
            }
        }
        private int maximalRectangle(char[][] matrix,int start_i,int start_j,int end_i,int end_j) {
            while (true)
            {
                boolean RE = isRightExpendable(matrix,start_i,start_j,end_i,end_j),
                        BE = isBottomExpendable(matrix,start_i,start_j,end_i,end_j),
                        TE = isTopExpendable(matrix,start_i,start_j,end_i,end_j),
                        LE = isLeftExpendable(matrix,start_i,start_j,end_i,end_j);
                if(TE)
                {
                    start_j = start_j -1;
                    continue;
                }
                if(LE)
                {
                    start_i = start_i-1;
                    continue;
                }
                if(RE) {
                    end_i = end_i + 1;
                    continue;
                }
                if(BE) {
                    end_j  = end_j + 1;
                    continue;
                }
                int area_new = (end_i - start_i + 1) * (end_j - start_j + 1);
                this.area = Math.max(area_new,this.area);
                markSelectedStart(matrix,start_i,start_j,end_i,end_j);
                return this.area;
            }

        }
        public int maximalRectangle(char[][] matrix) {
            this.height = matrix.length;
            if(this.height==0) return 0;
            this.width  = matrix[0].length;
            int start_i = 0, start_j = 0;
            while(true)
            {
                boolean newStartFound = false;
                for(int j=start_j;j<height;j++)
                {
                    int i=(j==start_j)?start_i:0;
                    for(;i<width;i++)
                    {
                        if(matrix[j][i]=='1') {
                            start_i = i;
                            start_j = j;
                            newStartFound = true;
                            break;
                        };
                    }
                    if(newStartFound)
                        break;
                }

                 
                if(!newStartFound) break;
                int area_new = maximalRectangle(matrix, start_i, start_j, start_i, start_j);
                this.area = Math.max(area_new,this.area);
            }
            return this.area;
        }
}