package lc3xx;

import org.junit.Test;

public  class lc313
{

    @Test
    public void test1() {
        testcase(12,new int[] {2,7,13,19});
    }

    private void testcase(int n, int[] primes) {
        Solution s = new Solution();
        int result = s.nthSuperUglyNumber(n,primes);
        System.out.println(result);
    }

    class Solution {
        private int min(int[] candidates) {
            int m = candidates[0];
            for(int i : candidates)
                m = Math.min(m,i);
            return m;
        }

        public int nthSuperUglyNumber(int n, int[] primes) {
            int [] uglyNumbers = new int[n];
            uglyNumbers[0] = 1;

            int k [] = new int[primes.length];
            int d [] = primes.clone();
            for (int i = 1; i < n; i++) {
                int dm = this.min(d);
                uglyNumbers[i] = dm;
                for (int j = 0; j < k.length; j++) {
                    while (dm >= d[j]) {
                        k[j]++;
                        d[j] = uglyNumbers[k[j]]*primes[j];
                    }
                }

            }
            return uglyNumbers[n-1];
        }
    }

}
