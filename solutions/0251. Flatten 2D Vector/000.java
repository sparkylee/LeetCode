class Vector2D {
    int i = 0, j = 0;
    int[][] vec;
    boolean firstNext = true;
    int ii = 0, jj = -1;
    void getNext() {
        while(ii < vec.length && jj>=vec[ii].length-1) {
          ii++;
          jj=-1;
        }
        jj++;
    }
    public Vector2D(int[][] vec) {
        this.vec = vec;
        getNext();
    }
    
    public int next() {
        int nextValue = vec[ii][jj];
         getNext();
        return nextValue;
    }
    
    public boolean hasNext() {
        return ii < vec.length && jj < vec[ii].length;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */