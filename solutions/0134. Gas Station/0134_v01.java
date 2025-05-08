class Solution {

    private int next(int i)
    {
        return (i+1)%len;
    }
    private int prev(int i)
    {
        return (i-1+len)%len;
    }

    int len;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        this.len = gas.length;
        int [] net = new int[gas.length];
        int start = 0;
        for(int i=0;i<gas.length;i++)
        {
            net[i]=gas[i]-cost[i];
            if(net[i]>0) start = i;
        }
        int i = start;
        int end  = next(start);
        int sum = net[start];

        while(start!=end)
        {
            if(sum+net[end]>=0)
            {
                sum += net[end];
                end = next(end);
            }
            else
            {
                start = prev(start);
                sum += net[start];

            }
        }
        return sum>=0?start:-1;
    }
}