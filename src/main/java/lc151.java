import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc151
{
    @Test
    public void test1()
    {
        tc("the sky is blue");
        tc("     ");
        tc("  ds   ");
        tc("1");
        tc("     1        ");

    }

    private void tc(String  str)
    {
        Solution s = new Solution();
        String strReversed = s.reverseWords(str);
        System.out.println(strReversed + "done");
    }
    class Solution {
        public String reverseWords(String s) {
            String [] strArray = s.trim().split("\\s+" );
            StringBuffer sb =  new StringBuffer();
            for(int i=strArray.length-1;i>=0;i--)
            {
                sb.append(strArray[i]);
                if(i!=0)
                    sb.append(' ');
            }
            return sb.toString();
        }
    }

}
