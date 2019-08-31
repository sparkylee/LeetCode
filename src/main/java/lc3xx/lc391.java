package lc3xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc391
{

    @Test
    public void test1()
    {
        int [][] rectangles = { {1,1,3,3}, {3,1,4,2}, {3,2,4,4}, {1,3,2,4}, {2,3,3,4} };
        test(rectangles);
    }
    @Test
    public void test2()
    {
        int [][] rectangles = { {1,1,2,3}, {1,3,2,4},{3,1,4,2}, {3,2,4,4} };
        test(rectangles);
    }
    @Test
    public void test3()
    {
        int [][] rectangles = { {1,1,3,3}, {3,1,4,2}, {1,3,2,4}, {3,2,4,4} };
        test(rectangles);
    }
    @Test
    public void test4()
    {
        int [][] rectangles = { {1,1,3,3}, {3,1,4,2},{1,3,2,4},{2,2,4,4}};
        test(rectangles);
    }

    @Test
    public void test5()
    {
        int [][] rectangles = {{0,0,1,1},{0,0,1,1},{0,2,1,3}};
        test(rectangles);
    }

    private void test(int [][] rectangles)
    {
        Solution s = new Solution();
        boolean result  = s.isRectangleCover(rectangles);
        System.out.println("dfasd:"+result);
    }
    class Solution
    {
        private int[] getOverallRect(List<int[]> rects)
        {
            if(rects==null || rects.size()<1) return null;
            int [] rec = rects.get(0).clone();
            for(int i=1;i<rects.size();i++)
            {
                int [] r1 = rects.get(i);
                rec[0] = Math.min(rec[0],r1[0]);
                rec[1] = Math.min(rec[1],r1[1]);
                rec[2] = Math.max(rec[2],r1[2]);
                rec[3] = Math.max(rec[3],r1[3]);
            }
            return rec;
        }
        private void divideRects(List<int[]> rects,List<int[]> r1,List<int[]> r2,List<int[]> ri,int threshold,boolean vh)
        {
            for(int [] rec: rects)
            {
                if(vh){//vetical
                    if (rec[2]<=threshold)
                    {
                        r1.add(rec);
                        continue;
                    }
                    if (rec[0]>=threshold)
                    {
                        r2.add(rec);
                        continue;
                    }
                    ri.add(rec);
                }
                else//horizontal
                {
                    if (rec[3]<=threshold)
                    {
                        r1.add(rec);
                        continue;
                    }
                    if (rec[1]>=threshold)
                    {
                        r2.add(rec);
                        continue;
                    }
                    ri.add(rec);
                }
            }
        }
        private boolean isRectangleOverlapNaive(List<int[]> rectangles)
        {
            for(int i=0;i<rectangles.size();i++)
            {
                int [] rx = rectangles.get(i);
                for(int j=i+1;j<rectangles.size();j++)
                {
                    int [] ry = rectangles.get(j);
                    if(isTwoRectOverlap(rx,ry)) return true;
                }
            }
            return false;
        }
        private boolean isRectangleNoOverlap(List<int[]> rectangles,boolean vh) {
            if(rectangles==null || rectangles.size()<=1) return true;
            List<int[]> r1 = new ArrayList<int[]>(),r2= new ArrayList<int[]>(),ri= new ArrayList<int[]>();
            int [] rectOverall = getOverallRect(rectangles);
            int threshold_vetical = (rectOverall[0]+rectOverall[2])/2;
            int threshold_horizontal = (rectOverall[1]+rectOverall[3])/2;
            int threshold = vh?threshold_vetical:threshold_horizontal;
            divideRects(rectangles,r1,r2,ri,threshold,vh);
            if(ri.size()==rectangles.size()|| r1.size()==rectangles.size() || r2.size()==rectangles.size())
                {
                vh=!vh;
                threshold = vh?threshold_vetical:threshold_horizontal;
                ri.clear();
                r1.clear();
                r2.clear();
                divideRects(rectangles,r1,r2,ri,threshold,vh);
            }
            if(ri.size()==rectangles.size()|| r1.size()==rectangles.size() || r2.size()==rectangles.size())
                return !isRectangleOverlapNaive(rectangles);

            if(isRectOverlap(ri,r1) || isRectOverlap(ri,r2)) return false;

            return isRectangleNoOverlap(r1, !vh) && isRectangleNoOverlap(r2, !vh) && isRectangleNoOverlap(ri, !vh);
        }
        private int getArea(int [] rect)
        {
            return (rect[2] - rect[0])*(rect[3]-rect[1]);
        }
        private boolean isTwoRectOverlap(int[] rx,int [] ry)
        {
            if(ry[3]<=rx[1]) return false;
            if(ry[1]>=rx[3]) return false;
            if(ry[0]>=rx[2]) return false;
            if(ry[2]<=rx[0]) return false;
            return true;
        }
        private boolean isRectOverlap(List<int[]> rxs, List<int[]> rys)
        {
            for(int [] rx: rxs)
                for(int [] ry: rys)
                {
                    if(isTwoRectOverlap(rx,ry)) return true;
                }
            return false;
        }
        public boolean isRectangleCover(int[][] rectangles) {
            List<int[]> rects = new ArrayList<int[]>();
            int area_sum  = 0;

            for(int [] rec: rectangles)
            {
                rects.add(rec);
                area_sum += getArea(rec);
            }
            int area_overall = getArea(getOverallRect(rects));
            if(area_overall!=area_sum) return false;
            return isRectangleNoOverlap(rects,true);
        }

    }



}
