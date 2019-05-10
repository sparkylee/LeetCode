import org.junit.Test;

public  class lc319
{

    @Test
    public void test1()
    {
        tc(3);
        tc(3);
    }

    private void tc(int n)
    {
        Solution s = new Solution();
        System.out.println(s.bulbSwitch(n));
    }

    class Solution {
        public int bulbSwitch(int n) {
            return (int)(Math.sqrt(n));
        }
    }

}
