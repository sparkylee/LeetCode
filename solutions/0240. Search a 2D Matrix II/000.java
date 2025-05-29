class Solution {
        int tlx, tly,  brx, bry;
        int target;
        int [][] matrix;
        boolean found =false;
        private int trimTop(int x1,int x2)
        {
            if(x1 == x2) return x1;
            int x = (x1+x2+1)/2;
            if(this.matrix[this.tly][x]>this.target)
                return trimTop(x1,x-1);
            if(this.matrix[this.tly][x]>this.target)
            {
                found = true;
                return x;
            }
            return trimTop(x,x2);
        }
        private int trimLeft(int y1,int y2 )
        {
            if(y1 == y2) return y1;
            int y = (y1+y2+1)/2;
            if(this.matrix[y][this.tlx]>this.target)
                return trimLeft(y1,y-1);
             if(this.matrix[y][this.tlx]==this.target)
             {
                 found=true;
                 return y;
             }
            return trimLeft(y,y2);
        }
        private int trimRight( int y1,int y2)
        {
            if(y1 == y2) return y1;
            int y = (y1+y2)/2;
            if(this.matrix[y][this.brx]<this.target)
                return trimRight(y+1,y2);
            if(this.matrix[y][this.brx]==this.target)
            {
                found = true;
                return y;
            }

            return trimRight(y1,y);

        }
        private int trimBottom(int x1,int x2 )
        {
            if(x1 == x2) return x1;

            int x = (x1+x2)/2;
            if(this.matrix[this.bry][x]<this.target)
                return trimBottom(x+1,x2);
            if(this.matrix[this.bry][x]==this.target)
            {
                found = true;
                return x;
            }
            return trimBottom(x1,x);
        }
        private void trimLarger( )
        {
            this.brx=trimTop(this.tlx,this.brx);
            this.bry=trimLeft(this.tly,this.bry);
        }
        private void trimSmaller( )
        {
            this.tlx=trimBottom(this.tlx,this.brx);
            this.tly=trimRight(this.tly,this.bry);
        }

        public boolean searchMatrix( ) {

            if(tlx==brx && tly==bry)
                return this.matrix[tly][tlx]==target;
            trimLarger();
            trimSmaller();
            if(found) return true;
            return searchMatrix( );
        }
        public boolean searchMatrix(int[][] matrix, int target) {
           if(matrix==null || matrix.length<1 || matrix[0].length<1) return false;
           this.target = target;
           this.matrix = matrix;
           this.tlx = 0;
           this.tly = 0;
           this.brx = matrix[0].length-1;
           this.bry = matrix.length-1;
           return searchMatrix();
        }
}