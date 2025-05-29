class Solution {
 
        int m,n;
        private int getMax(int[][] heightMap)
        {
            int mv = 0;
            for(int j=0;j<heightMap.length;j++)
            {
                for(int i=0;i<heightMap[j].length;i++)
                {
                    mv=Math.max(heightMap[j][i],mv);
                }
            }
            return mv;
        }
        private void preFill(int[][] RainMap,int mv)
        {
            for(int j=0;j<RainMap.length;j++)
            {
                for(int i=0;i<RainMap[j].length;i++)
                {
                    RainMap[j][i] = mv;
                }
            }
        }
        private void drainFromSingleCell(int[][] heightMap,int[][] RainMap,int i,int j)
        {
            if(i<0 || i>= n || j<0 || j>=m) return;
            int reserved = RainMap[j][i]-heightMap[j][i];
            if(reserved==0) return;
            int l = (i-1>=0)?RainMap[j][i-1]:0,
                r = (i+1<n)?RainMap[j][i+1]:0,
                t=  (j-1>=0)?RainMap[j-1][i]:0,
                b=  (j+1<m)?RainMap[j+1][i]:0;
            int mv = Math.min(r,l);
            mv = Math.min(t,mv);
            mv = Math.min(b,mv);
            int drain_allowed = RainMap[j][i] - mv;
            if(drain_allowed<=0) return;
            int drain = drain_allowed > reserved?reserved:drain_allowed;
//            if(i==2 && j==1)
//                System.out.println("dfa");
            RainMap[j][i] = RainMap[j][i] - drain;
            drainFromSingleCell(heightMap,RainMap,i-1,j);
            drainFromSingleCell(heightMap,RainMap,i+1,j);
            drainFromSingleCell(heightMap,RainMap,i,j-1);
            drainFromSingleCell(heightMap,RainMap,i,j+1);

        }
        public int trapRainWater(int[][] heightMap) {
            m = heightMap.length;
            if(m<1) return 0;
            n= heightMap[0].length;
            int[][] RainMap = new int[m][n];
            int maxLevel = getMax(heightMap);
            preFill(RainMap,maxLevel);
            for(int j=0;j<RainMap.length;j++)
                for(int i=0;i<RainMap[j].length;i++)
                    drainFromSingleCell(heightMap,RainMap,i,j);

            int sum = 0;
            for(int j=0;j<RainMap.length;j++)
                for(int i=0;i<RainMap[j].length;i++)
                    sum += (RainMap[j][i] - heightMap[j][i]);

            return sum;
        }
}