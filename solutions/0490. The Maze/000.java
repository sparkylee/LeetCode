class Solution {
    void addStep(int [] pos, int [] dir) {
        pos[0] += dir[0];
        pos[1] += dir[1];
    }
    void minusStep(int [] pos, int [] dir) {
        pos[0] =  pos[0] - dir[0];
        pos[1] =  pos[1] - dir[1];
    }
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(start[0]==destination[0] && start[1]==destination[1])
            return true;
        maze[start[0]][start[1]] = -1;
        // System.out.println("position "+start[0]+","+start[1]+" is checked!");
        int [][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int [] pos = new int[2];
        for(int i = 0; i<4;i++) {
            pos[0] = start[0];
            pos[1] = start[1];
            do{
                addStep(pos, dirs[i]);
            } while(pos[0]>=0 && pos[0]<maze.length && pos[1]>=0 && pos[1]<maze[0].length && maze[pos[0]][pos[1]] <=0);
            minusStep(pos, dirs[i]);
            int v = maze[pos[0]][pos[1]];
            if(v==0 && hasPath(maze,pos, destination))
                return true;
        }
        return false;
    }
}