/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
     private int [] getSlope(Point p1,Point p2)
        {
            return new int []{p2.x-p1.x,p2.y-p1.y};
        }
        private void simplifySlope(int [] slope)
        {
            //singular situation
            if(slope[0]==0&& slope[1]==0) return;
            if(slope[0]==0)
            {
                slope[1] = 1; return;
            }
            if(slope[1]==0)
            {
                slope[0] = 1; return;
            }
            if(slope[0]<0)//polarized
            {
                slope[0] = -slope[0];
                slope[1] = -slope[1];
            }
            int g = gcd(Math.abs(slope[0]),Math.abs(slope[1]));
            slope[0] = slope[0]/g;
            slope[1] = slope[1]/g;
        }
        private long getSlopeHash(int [] slope)
        {
            return (((long)slope[0])<<32) + slope[1];
        }
        private int gcd(int a, int b)
        {

            int max_value = Math.max(a,b);
            int min_value = Math.min(a,b);
            int mod = max_value%min_value;
            if(mod==0) return min_value;
            return gcd(min_value,mod);
        }
        private long getHashedSlopeBtwPoints(Point p1, Point p2)
        {
            int [] slope = getSlope(p1,p2);
            simplifySlope(slope);
            return getSlopeHash(slope);
        }
        public int maxPoints(Point[] points) {
            if(points==null || points.length<1 ) return 0;
            int max_point_count = 1;
            for(int i=0;i<points.length;i++)
            {
                Point pi = points[i];
                HashMap<Long,Integer> slopeCountMap = new HashMap<Long, Integer>();
                int duplicate_count = 0;
                int max_extra_point_count = 0;
                for(int j=i+1;j<points.length;j++)
                {
                    Point pj = points[j];
                    long slopeHash = getHashedSlopeBtwPoints(pi,pj);
                    if(slopeHash==0)
                        duplicate_count++;
                    else {
                        int nextValue = slopeCountMap.getOrDefault(slopeHash, 0)+1;
                        max_extra_point_count = Math.max(max_extra_point_count, nextValue);
                        slopeCountMap.put(slopeHash, nextValue);
                    }
                }
                int max_point_pi = 1 + duplicate_count +  max_extra_point_count;
                max_point_count = Math.max(max_point_pi,max_point_count);
            }
            return max_point_count;
        }
}