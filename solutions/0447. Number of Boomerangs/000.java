class Solution {
      class Point
        {
            int x,y;
            Point(int x, int y )
            {
                this.x= x;
                this.y = y;
            }
        }
        public int numberOfBoomerangs(int[][] points) {
            int n = points.length;
            //System.out.println("n="+n);
            Map<Integer, List<Point>> dis2ptMapping = new HashMap<>();

            int count = 0;
            long start1 = System.currentTimeMillis();
            for(int i =0;i <n ; i++)
            {
                for(int j = i+1;j<n;j++ )
                {
                    int x =  points[i][0]-points[j][0];
                    int y =  points[i][1]-points[j][1];
                    int dis2 = x*x+y*y;
                    if(dis2ptMapping.get(dis2)==null) dis2ptMapping.put(dis2,new ArrayList<>());
                    dis2ptMapping.get(dis2).add(new Point(i,j));
                }
            }
            long end1 = System.currentTimeMillis();

//            System.out.println("Time taken: "+ (end1-start1) +" milliseconds");
           

            for(Map.Entry<Integer,List<Point>> entry:  dis2ptMapping.entrySet())
            {
                List<Point> pList = entry.getValue();
                if(pList.size()<2) continue;

                for(int i=0;i<pList.size();i++)
                {
                    for(int j=i+1;j<pList.size();j++)
                    {
                        if(pList.get(i).y==pList.get(j).x
                        || pList.get(i).x==pList.get(j).y
                        || pList.get(i).x==pList.get(j).x
                          || pList.get(i).y==pList.get(j).y)
                            count++;
                    }

                }


            }
            
           
//            System.out.println("Time taken: "+ timeElapsed2.toMillis() +" milliseconds");
            return count*2;
        }

}