class Solution {
    public int minArea(char[][] image, int x, int y) {
        int [][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
        Stack<int []> stack = new Stack();
        stack.add(new int [] {x,y});
        image[x][y]=0;
        int lm = image[0].length, rm = -1, tm = image.length, bm = -1;
        while (!stack.isEmpty()) {
            int  [] pixel = stack.pop();
            lm = Math.min(pixel[1], lm);
            rm = Math.max(pixel[1], rm);
            tm = Math.min(pixel[0], tm);
            bm = Math.max(pixel[0], bm);
            for(int i=0;i<dirs.length;i++) {
                int x1 = pixel[0] + dirs[i][0];
                int y1 = pixel[1] + dirs[i][1];
                if(x1<0 || x1>=image.length || y1<0 || y1>= image[0].length || image[x1][y1] <= '0')
                    continue;
                stack.add(new int [] {x1,y1});
                image[x1][y1] = 0;
            }
        }
        // System.out.println("lm="+lm+" rm="+rm+" tm="+tm+" bm="+bm);
        return (rm-lm+1) * (bm-tm+1);
    }
}