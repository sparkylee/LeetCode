class Solution {
    int m,n;
    private void exploreBuildingDistance(int[][] grid, int [] building_pos, int building_counter, int [][][] gridVal) {
        List<int[]> current_list = new ArrayList<>();
        int [][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        current_list.add(building_pos);
        gridVal[building_pos[0]][building_pos[1]][0] = building_counter;
        int distance = 1;
        while(!current_list.isEmpty()) {
            List<int[]> next_list = new ArrayList<>();
            for(int i=0;i<current_list.size();i++) {
                int [] pos = current_list.get(i);
                for(int [] dir : dirs) {
                    int row = pos[0] + dir[0];
                    int col = pos[1] + dir[1];
                    if(row<0 || col < 0 || row >= this.m || col >= this.n) // out of boundary
                        continue;    
                    if(grid[row][col]!=0) // cannot pass through of this occupied land.
                        continue;
                    if(gridVal[row][col][0]==building_counter) // already visited by this building
                        continue; 
                    int [] gridValue = gridVal[row][col];
                    int prev_building_counter = gridValue[0];
                    if(prev_building_counter==building_counter) //already calculated
                        continue;
                    if(prev_building_counter!=building_counter-1) { //missing some other building
                        grid[row][col] = 2; // marked as unreachable.
                        continue;
                    }
                    gridValue[0] = building_counter;
                    gridValue[1] += distance;               
                    next_list.add(new int[] {row,col});
                }
            }
            current_list = next_list;           
            distance ++;
        }
    }
    public int shortestDistance(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        //0: building_counter, 1: total_distance to all buildings
        int [][][] gridVal = new int[this.m][this.n][2];        
        int building_counter = 1;
        for(int i=0;i<this.m;i++) {
            for(int j=0;j<n;j++) {
                int val = grid[i][j];
                if(val==1) {
                    exploreBuildingDistance(grid,new int[] {i,j},building_counter,gridVal);
                    building_counter++;
                }
            }
        }
        building_counter--;
        int total_travel_distance = -1;
        // System.out.println(Arrays.deepToString(gridVal));
        for(int i=0;i<this.m;i++) {
            for(int j=0;j<n;j++) {
                int [] gridValue = gridVal[i][j];
                if(gridValue[0]==building_counter && grid[i][j]==0 && ((total_travel_distance==-1) || gridValue[1] < total_travel_distance)) {
                    total_travel_distance = gridValue[1] ;
                }
            }
        }
        return total_travel_distance;
    }
}