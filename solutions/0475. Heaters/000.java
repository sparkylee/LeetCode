class Solution {
    class Node implements Comparable<Node>  {
            int pos;
            boolean isHeater;
            int dis;
            Node(int pos,boolean isHeater,int dis) {
                this.pos= pos;
                this.isHeater = isHeater;
                this.dis = dis;
            }
            @Override
            public int compareTo(Node n1) {
                return this.pos - n1.pos;
            }
        }
        public int findRadius(int[] houses, int[] heaters) {
            List<Node> hhList = new ArrayList<>();
            for(int i=0;i<houses.length;i++)
            {
                Node n = new Node(houses[i],false,-1);
                hhList.add(n);
            }
            for(int i=0;i<heaters.length;i++)
            {
                Node n = new Node(heaters[i],true,-1);
                hhList.add(n);
            }
            Collections.sort(hhList);
            int i = 0,j=1;
            int len = hhList.size();
            for(;i<len-1;i++)
            {
                j = i+1;
                Node ni = hhList.get(i),nj = hhList.get(j);
                if(ni.isHeater && nj.isHeater)//both are heaters
                {
                    //do nothing
                }
                if(!ni.isHeater && nj.isHeater)//left house right heater, need to look back to update the distance.
                {
                    int k = i;
                    while(k>=0)
                    {
                        Node nk = hhList.get(k);
                        int dis_new  = nj.pos - nk.pos;
                        if(!nk.isHeater && (nk.dis==-1 || nk.dis > dis_new))//is house and the new distance is smaller
                        {
                            nk.dis = dis_new;
                            k--;
                        }
                        else break;////stop looking back
                    }
                }
                if(ni.isHeater && !nj.isHeater)
                {
                    nj.dis = nj.pos - ni.pos;
                }
                if(!ni.isHeater && !nj.isHeater)
                {
                    if(ni.dis!=-1)
                    {
                        nj.dis = nj.pos - ni.pos + ni.dis;
                    }
                }
            }
            int m = -1;
            for(Node ni:hhList)
            {
                if( ni.dis>m) m=ni.dis;
            }
            return m;

        }
}