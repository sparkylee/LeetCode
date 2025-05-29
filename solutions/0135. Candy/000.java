class Solution {
    public int candy(int[] ratings) {
           int [] candies = new int[ratings.length];
            for(int i=0;i<ratings.length;i++)
            {

                boolean leftEqual = (i-1)<0 || ratings[i-1]==ratings[i];
                boolean rightEqual = (i+1)>=ratings.length || ratings[i+1]==ratings[i];
                if(leftEqual && rightEqual) {
                    candies[i]= 1;
                    continue;
                }

                boolean leftHigher = (i-1)<0 || ratings[i-1]>=ratings[i];
                boolean rightHigher = (i+1)>=ratings.length || ratings[i+1]>=ratings[i];
                if(leftHigher && rightHigher)
                {
                    candies[i]= 1;
                    int j=i-1;
                    for(;j>=0;j--)
                        if(ratings[j]>ratings[j+1])
                            candies[j]=Math.max(candies[j],candies[j+1]+1);
                        else
                            break;
                    for(j=i+1;j<ratings.length;j++)
                        if(ratings[j]>ratings[j-1])
                            candies[j]=candies[j-1]+1;
                        else break;

                }
            }
            int sum=0;
            for(int c: candies) sum+=c;
            return sum;
    }
}