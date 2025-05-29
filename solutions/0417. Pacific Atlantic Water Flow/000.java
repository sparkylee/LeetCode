class Solution {
    int m,n;
    int [][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    void markPacificAtlantic(int[][] heights, int i, int j, int mask) {       
        int val = heights[i][j];
        heights[i][j] |= mask;
        int height = val & 0xfffffff;
        for(int k=0;k<this.dirs.length;k++) {
            int ii = i + dirs[k][0];
            int jj=  j + dirs[k][1];
            if(ii<0 || ii>=m || jj<0 || jj>= n) {
                continue;
            }
            int val_neighbor = heights[ii][jj];
            int height_neighbor = val_neighbor &  0xfffffff;
            boolean connected_neighbor = (val_neighbor & mask) != 0x0;
            if(!connected_neighbor && height<=height_neighbor) {
                markPacificAtlantic(heights,ii,jj,mask);
            }
        }
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {       
        this.m = heights.length;
        this.n = heights[0].length;        
        int [] masks = {0x80000000, 0x40000000};
        for(int i=0;i<n;i++) {
            markPacificAtlantic(heights,0,i,masks[0]);
        }
        for(int i=0;i<m;i++) {
            markPacificAtlantic(heights,i,0,masks[0]);            
        }
      
         for(int i=0;i<n;i++) {
            markPacificAtlantic(heights,m-1,i,masks[1]);            
        }
        for(int i=0;i<m;i++) {
            markPacificAtlantic(heights,i,n-1,masks[1]);                       
        }              

        List<List<Integer>> results = new ArrayList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                boolean connected_both_ocean = (heights[i][j] & 0xC0000000) == 0xC0000000;
                if( (heights[i][j] & 0xC0000000) == 0xC0000000) {
                    List<Integer> lst =  new ArrayList<>();
                    lst.add(i);
                    lst.add(j);
                    results.add(lst);
                }
            }
        }

        return results;
    }
}