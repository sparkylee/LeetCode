package lc2xx;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public  class lc274
{
    @Test
    public void test5() {
        tc(new int [] {3,0,6,1,5} );
        tc(new int [] {} );
        tc(new int [] {0} );
        tc(new int [] {1} );
        tc(new int [] {1,2} );
    }

    private void tc(int[] citations) {
        Solution s = new Solution();
        System.out.println(s.hIndex(citations));
    }

    class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            for (int i = 0; i < citations.length; i++) {
                int h = citations.length - i;
                if(citations[i]>=h && (i-1<0 || citations[i-1]<=h ))
                    return h;
            }
            return 0;
        }
    }
}
