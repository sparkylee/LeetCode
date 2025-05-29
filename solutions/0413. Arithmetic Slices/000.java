class Solution {
    private int [] n2slice;
    private void fillN2Slice(int n)
    {
        n2slice = new int[n];
        n2slice[0] = 0;
        n2slice[1] = 0;
        n2slice[2] = 1;
        for(int i=3;i<n;i++)
            n2slice[i]=n2slice[i-1]+i-1;
    }
    public int numberOfArithmeticSlices(int[] A) {
        if(A==null || A.length<3)
            return 0;
        List<Integer> countArray = new ArrayList<>();
        int i =0;
        int count = 0;
        while(i+2<A.length)
        {
            if(A[i+1] - A[i] == A[i+2] - A[i+1])
                count++;
            else {
                if (count > 0)
                    countArray.add(count + 2);
                count=0;
            }
            i++;
        }
        if(count>0)
            countArray.add(count+2);
        if(countArray.size()==0)
            return 0;
        int m = Collections.max(countArray);
        fillN2Slice(m);
        int sum = 0;
        for(Integer c : countArray)
        {
            sum += n2slice[c-1];
        }
        return sum;
    }
}