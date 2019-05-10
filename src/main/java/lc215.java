import org.junit.Test;

public  class lc215
{


    @Test
    public void tet1()
    {

        tc(new int[]{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6},20);
        tc(new int[]{3,2,1,5,6,4},2);
        tc(new int[]{3,2,3,1,2,4,5,5,6},4);
        tc(new int[]{3},1);
        tc(new int[]{3,2},1);
        tc(new int[]{3,2},2);
    }
    private void tc(int [] nums,int k)
    {
        Solution s = new Solution();
        int result = s.findKthLargest(nums,k);
        System.out.println(result);
    }

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            if(nums==null || nums.length<1) return 0;
            if(k<1 || k>nums.length) return 0;
            int start = 0,end = nums.length-1;
            while (true)
            {
                int a=(start+end)/2,b=a;
                int i=start-1,j= end+1;
                int base = nums[a];
                while (true) {
                    if (i + 1 < a && nums[i + 1] == base) {
                        nums[i + 1] = nums[a - 1];
                        nums[a - 1] = base;
                        a--;
                    }
                    if (j - 1 > b && nums[j - 1] == base) {
                        nums[j-1] = nums[b+1];
                        nums[b+1] = base;
                        b++;
                    }
                    while (i + 1 <= end && nums[i + 1] < base) i++;
                    while (j - 1 >= start && nums[j - 1] > base) j--;
                    while ((a - 1 >= start) && nums[a - 1] == base) a--;
                    while ((b + 1 <= end) && nums[b + 1] == base) b++;
                    while(i + 1 <= end && nums[i + 1] > base && j - 1 >= start && nums[j - 1] < base)
                    {
                        int tmp = nums[i + 1];
                        nums[i+1] = nums[j-1];
                        nums[j-1] = tmp;
                        i++;
                        j--;
                    }
                    if(i+1==a && j-1>b)
                    {
                        nums[a]=nums[b+1];
                        nums[b+1] = base;
                        a++;
                        b++;
                        continue;
                    }
                    if(i+1<a && j-1==b)
                    {
                        nums[b]=nums[a-1];
                        nums[a-1] = base;
                        a--;
                        b--;
                        continue;
                    }

                    if (i + 1 == a && j - 1 == b)
                        break;

                }
                int size_larger  = end - j + 1;
                int size_equal   = b-a+1;
                int size_smaller = i- start + 1;
                if(k>size_larger && k <= (size_larger+size_equal) ) // in the equal group
                {
                    return nums[a];
                }
                if(k<=(size_larger)) // in the larger group
                {
                    start = j;
                }
                else
                {
                    end = i;
                    k = k- (size_larger+size_equal);
                }
            }

        }
    }
}
