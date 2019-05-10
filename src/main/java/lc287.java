import org.junit.Test;

public  class lc287
{

    @Test
    public void test1()
    {
        testcase(new int[]{1,3,4,2,2});
    }
    @Test
    public void test2()
    {
        testcase(new int[]{3,1,3,4,2});
    }
    @Test
    public void test3()
    {
        testcase(new int[]{3,3,3,4,2});
    }
    @Test
    public void test4()
    {
        testcase(new int[]{1,1});
    }
    @Test
    public void test5()
    {
        testcase(new int[]{26,2,9,20,31,7,14,32,37,15,29,6,12,38,48,22,19,45,42,40,1,12,25,36,39,30,35,4,
                27,12,49,16,47,3,44,41,8,17,21,23,10,43,12,34,24,28,33,13,46,11});
    }
    private void testcase(int [] nums)
    {
        Solution s = new Solution();
        int result = s.findDuplicate(nums);
        System.out.println(result);
    }

    class Solution {
        public int findDuplicate(int[] nums) {
            int n = nums.length-1;
            int n_index = n-1;
            int len = (n_index + 64) /64;
            long [] bitArray = new long[len];
            long bits = 0;
            for(int i=0;i<nums.length;i++)
            {
                int j =  (nums[i] - 1)/64;
                bits = bitArray[j];
                int k = (nums[i] - 1)%64;
                long value = (bits>>k) & 0x1;
                if(value!=0)
                    return nums[i];
                bitArray[j] = bitArray[j] | (((long)1)<<k);
            }
            return 0;
        }
    }

}
