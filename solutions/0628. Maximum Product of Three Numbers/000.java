class Solution {
    
    public int maximumProduct(int[] nums) {
    int p0=0,i0=-1, p1=0, i1=-1, p2=0,i2=-1, n0=0, j0=-1, n1=0, j1=-1;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=p0) {
                p0=nums[i];
                i0=i;
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=p1 && nums[i]<=p0 && i!=i0)
            {
                p1=nums[i];
                i1=i;
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=p2 &&  nums[i]<=p0 &&  nums[i]<=p1 && i!=i0 && i!=i1)
            {
                p2=nums[i];
                i2=i;
            }
        }

        for(int j=0;j<nums.length;j++)
        {
            if(nums[j]<=n0) {
                n0=nums[j];
                j0=j;
            }
        }
        for(int j=0;j<nums.length;j++)
        {
            if(nums[j]<=n1 && nums[j]>=n0 && j!=j0)
            {
                n1=nums[j];
                j1=j;
            }
        }
        int m0 =(i0>=0 && i1>=0 && i2>=0)? p0*p1*p2 : -1,
                m1= (i0>=0 && j0>=0 && j1>=0)? p0*n0*n1 : -1;
        int m3= Math.max(m0,m1);
        if(m3>=0) {
            return m3;
        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==0) return 0;
        }
        p0=n0;
        p1=n0;
        p2=n0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>p0) {
                p0=nums[i];
                i0=i;
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            if( nums[i]>=p1 && nums[i]<=p0 && i!=i0)
            {
                p1=nums[i];
                i1=i;
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            if( nums[i]>=p2 &&  nums[i]<=p0 &&  nums[i]<=p1 && i!=i0 && i!=i1)
            {
                p2=nums[i];
            }
        }
        return p0*p1*p2;
    }
}