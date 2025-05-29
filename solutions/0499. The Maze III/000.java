class Solution {    
    class Node {
        int distance;
        String path;
        Node(int distance, String path) {
            this.distance = distance;
            this.path = path;
        }
    }
    int [][] dirs  = {{1,0}, {0,-1}, {0,1}, {-1,0} }; 
    String [] dstr = { "d", "l", "r", "u"};
    int convLoc(int row, int col) {
        return row | (col<<16);
    }
    int calDistance(int pos1, int pos2) {
        int row1 = pos1 & 0xffff;
        int col1 = pos1 >> 16;
        int row2 = pos2 & 0xffff;
        int col2 = pos2 >> 16;
        return Math.abs(row1-row2) +  Math.abs(col1-col2);
    } 
    int getNextPos(int[][] maze, int row, int col, int [] dir, int [] hole) {
        while(true) {
            int row_new = row + dir[0];
            int col_new = col + dir[1];          
            if((row==hole[0] && col == hole[1]) || row_new>=maze.length || row_new<0 || col_new >=maze[row_new].length || col_new < 0
            || maze[row_new][col_new] == 1 ) {
                return convLoc(row, col);
            }
            row = row_new;
            col = col_new;
        }
    }   
    void findShortestWayDistance(int[][] maze, int[] ball, int[] hole,  Map<Integer, Node> visited) {        
        Map<Integer, Node> locations = new HashMap<>();
        int loc_init = ball[0] | (ball[1]<<16);
        Node init = new Node(0, "");
        visited.put(loc_init,  init);
        locations.put(loc_init, init);
        int hole_loc = hole[0] | (hole[1]<<16);           
        while(true) {
            Map<Integer, Node> locations_new = new HashMap<>();
            for(Integer loc: locations.keySet()) {
                int row = loc & 0xffff;
                int col = loc >> 16;
                int pos_new = 0;
                Node current_node = locations.get(loc);
                String current_path     = current_node.path;
                int current_distance = current_node.distance;
                for(int i = 0; i< this.dirs.length;i++) {
                    pos_new = getNextPos(maze,row,col, this.dirs[i], hole);
                    if(pos_new==loc ){
                        continue;
                    }
                    int dis = calDistance(pos_new, loc);
                    int distance_new = dis + current_distance;
                    String path_new = current_path + dstr[i]; 
                    Node existing_node = visited.getOrDefault(pos_new, null);                    
                    if(existing_node!=null)  {
                        // System.out.println("comparing " + existing_node.path + " and " + path_new);
                        if(existing_node.distance < distance_new 
                            || (existing_node.distance == distance_new  && existing_node.path.compareTo(path_new) < 0 )) {
                                continue;
                            }
                    }
                    Node node_new = new Node(distance_new, path_new);
                    if(pos_new != hole_loc)
                        locations_new.put(pos_new, node_new);
                    visited.put(pos_new, node_new);
                }               
            }
            if(locations_new.isEmpty())
                return;
            locations = locations_new;
        }                
    }
    // public boolean findShortestWay(int[][] maze,  Set<Integer> visited, int row, int col, int[] hole,  int count, int target, List<String> sb) {
    //     if(count>=target)
    //         return false;    
    //     int hole_loc = hole[0] | (hole[1]<<16);
    //     int pos = row | ( col <<16);
    //     visited.add(pos);
    //     for(int i = 0; i< this.dirs.length;i++) {                
    //             int pos_new = getNextPos(maze,row,col, this.dirs[i], hole);
    //             if(pos_new==hole_loc) {
    //                 sb.add(dchars[i]);
    //                 return true;
    //             }
    //             if(visited.contains(pos_new)){
    //                 continue;
    //             }                            
    //             sb.add(dchars[i]);                
    //             boolean found = findShortestWay(maze, visited, row, col, hole,  count + 1, target, sb);
    //             if(found) 
    //                 return true;               
    //             sb.remove(sb.size() - 1);
    //         }
    //     visited.remove(pos);
    //     return false;
    // }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        Map<Integer, Node> visited =  new HashMap<>();
        findShortestWayDistance(maze, ball, hole, visited);
        // for(Integer loc: visited.keySet()) {
        //     int row = loc & 0xffff;
        //     int col = loc >> 16;
        //     Node node = visited.get(loc);
        //     System.out.println("Position row=" + row + " col=" + col + " path="+ node.path + " distance=" + node.distance);
        // }
        int hole_loc = hole[0] | (hole[1]<<16);           
        Node hole_node = visited.getOrDefault(hole_loc, null);
        if(hole_node==null)
            return "impossible";
        
        String path = hole_node.path;
        System.out.print(hole_node.distance);
        return path;
        // System.out.print("target="+target);
        // if(target==0)
        //     return "impossible";
        // Set<Integer> visited = new HashSet<>();  
        // int loc_init = ball[0] | (ball[1]<<16);
        // visited.add(loc_init);
        // List<String> sb = new ArrayList<>();
        // boolean found = findShortestWay(maze, visited,  ball[0], ball[1], hole, 0, target, sb);
        // if(found)
        //     return String.join("", sb);;
        //  return "impossible"; 
    }
}