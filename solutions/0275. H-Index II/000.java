class Solution {
    public int hIndex(int[] citations) {
       if(citations==null || citations.length<1) return 0;
            int start = 0, end = citations.length -1;
            while (start<=end)
            {
                int i = (start+end)/2;
                int h = citations.length - i;
                if(citations[i]>=h && (i-1<0 || citations[i-1]<=h ))
                    return h;
                if(citations[i]>=h)
                    end = i-1;
                else
                    start = i+1;
            }
            return 0;
    }
}