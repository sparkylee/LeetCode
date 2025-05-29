class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
          if(triangle.size()>1)
            {
                int size = triangle.size();
                for(int i=size-2;i>=0;i--)
                {
                    int s = triangle.get(i).size();
                    for(int j=0;j<s;j++)
                    {
                        int x = triangle.get(i).get(j);
                        int y1 = triangle.get(i+1).get(j);
                        int y2 = triangle.get(i+1).get(j+1);
                        int x_new = Math.min(y1,y2)+x;
                        triangle.get(i).set(j,x_new);
                    }
                }
            }
            return triangle.get(0).get(0);
    }
}