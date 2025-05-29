class Solution {
    public int hIndex(int[] citations) {
           Arrays.sort(citations);
            for(int i=0;i<citations.length;i++)
            {
                int h = citations.length - i;
                if(citations[i]>=h && (i-1<0 || citations[i-1]<=h ))
                    return h;
            }
            return 0;
    }
}