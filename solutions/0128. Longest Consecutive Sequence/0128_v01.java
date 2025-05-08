class Solution {
    int len;
    private long modn(long i)
    {

        return i%(long)len;
    }
    public int longestConsecutive(int[] nums) {
        if(nums.length<1) return 0;
        if(nums.length==1) return 1;
        len = nums.length;
        Set<Integer>[] nums_mod = new HashSet[nums.length];
        int min = nums[0];
        for(int i=0;i<nums_mod.length;i++)
        {
            if(nums[i]<min) min = nums[i] ;
            int index = nums[i]%len;
            int result = nums[i]/len;
            if(index <0)
            {
                index = index+len;
                result --;
            }
            if(nums_mod[index]==null) nums_mod[index] = new HashSet<>();
            nums_mod[index].add(result) ;
        }


        int m = 0;

        for(int i=0;i<nums_mod.length;i++)
        {

            if(nums_mod[i]==null) continue;
            for(int num:nums_mod[i])
            {
                int count = 1;
                int j =(int) modn(i+1);//forward searching
                while(j!=i)
                {
                    int num_new = j>i?num:(num+1);
                    if(nums_mod[j]!=null && nums_mod[j].contains(num_new))
                    {
                        count++;
                        nums_mod[j].remove(num_new);
                    }
                    else
                        break;
                    j=(int)modn(j+1);
                }
                j =(int) modn(i-1);//backward searching
                if(j<0) j+=len;
                while(j!=i)
                {
                    int num_new = j<i?num:(num-1);
                    if(nums_mod[j]!=null && nums_mod[j].contains(num_new))
                    {
                        count++;
                        nums_mod[j].remove(num_new);
                    }
                    else
                        break;
                    j=(int)modn(j-1);
                    if(j<0) j+=len;
                }
                m= count>m?count:m;
            }
        }
        return m;
    }
}