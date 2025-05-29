class Solution {
    int min_distant = -1;
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        shortestDistance(maze, start, destination,0);
        return min_distant;
    }

   
    void addStep(int [] pos, int [] dir) {
        pos[0] += dir[0];
        pos[1] += dir[1];
    }
    void minusStep(int [] pos, int [] dir) {
        pos[0] =  pos[0] - dir[0];
        pos[1] =  pos[1] - dir[1];
    }
    public void shortestDistance(int[][] maze, int[] start, int[] destination, int current_distant) {
        // System.out.println("position "+start[0]+","+start[1]+"  current_distant="+current_distant);
        if(start[0]==destination[0] && start[1]==destination[1]) {
            if(min_distant == -1 || current_distant< min_distant) {
                min_distant = current_distant;
            }
            return;
        }
        
        int [][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int [] pos = new int[2];
        for(int i = 0; i<4;i++) {
            pos[0] = start[0];
            pos[1] = start[1];
            int distant = 0;
            do{
                addStep(pos, dirs[i]);
                distant ++;
            } while(pos[0]>=0 && pos[0]<maze.length && pos[1]>=0 && pos[1]<maze[0].length && maze[pos[0]][pos[1]] <=0);
            minusStep(pos, dirs[i]);
            distant --;
            int v = maze[pos[0]][pos[1]];
            if(distant==0) 
                continue;
            int new_distant = current_distant+distant;
            if(v==0 || (new_distant>0 && new_distant<-v) ) {
                maze[pos[0]][pos[1]] = - new_distant;
                shortestDistance(maze,pos, destination, new_distant);
            }
                
        }
    }

}